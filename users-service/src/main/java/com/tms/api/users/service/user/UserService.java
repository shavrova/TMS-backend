package com.tms.api.users.service.user;

import com.tms.api.users.data.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto getUser(String id);

    UserDto createUser(UserDto userDto);

    UserDto getUserDetailsByEmail(String email);

    Page<UserDto> findPage(Pageable page);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String id);

}
