package com.tms.api.users.service.user;

import com.tms.api.users.data.dto.UserDto;
import com.tms.api.users.data.entity.User;
import com.tms.api.users.data.model.user.enums.RoleEnum;
import com.tms.api.users.data.model.user.enums.UserStatusEnum;
import com.tms.api.users.data.repository.UserRepository;
import com.tms.api.users.service.mapper.UserMapper;
import com.tms.api.users.service.test.TestServiceClient;
import com.tms.api.users.util.ClassUtils;
import com.tms.api.users.util.exception.EmailAlreadyExistsException;
import com.tms.api.users.util.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper mapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    TestServiceClient testService;


    @Override
    public UserDto getUser(String id) {
        UserDto userDto = mapper.entityToDto(userRepository.findByUserId(id).orElseThrow(() -> new UsernameNotFoundException("Can't find user with id " + id)));
        //  List<TestResponseModel> userTests = testService.getTests(userDto.getUserId()); //retrieving list of tests created by user from tests feign service
        //userDto.setUserTests(userTests);// setting retrieved list of tests to current user
        return userDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if (userRepository.findByEmail(userDto.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists");
        User user = mapper.dtoToEntity(userDto);//map dto to entity
        user.setRole(RoleEnum.USER.getCode());
        user.setStatus(UserStatusEnum.Active.getCode());
        user.setDeleted(false);
        UserDto createdUser = mapper.entityToDto(userRepository.save(user));//map entity to dto
        createdUser.setUserScenarios(Collections.emptyList());
        return createdUser;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email doesn't exist"));

        return mapper.entityToDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email doesn't exist"));

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(user.getRole()).getRoleName()));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);

    }

    @Override
    public Page<UserDto> findPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        Page<UserDto> dtos = users.map(user -> mapper.entityToDto(user));
        dtos.forEach(userDto -> userDto.setUserScenarios(testService.getTests(userDto.getUserId())));
        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDto updateUser(UserDto dto) {
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User id doesn't exist"));
        ClassUtils.setIfNotNull(dto::getFirstName, user::setFirstName);
        ClassUtils.setIfNotNull(dto::getLastName, user::setLastName);
        if (!dto.getEmail().equals(user.getEmail())) {
            if (!userRepository.findByEmail(dto.getEmail()).isPresent()) {
                ClassUtils.setIfNotNull(dto::getEmail, user::setEmail);
            } else throw new EmailAlreadyExistsException("This email address already registered.");
            userRepository.save(user);
        }
        return mapper.entityToDto(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User id doesn't exist"));
        userRepository.delete(user);
    }
}
