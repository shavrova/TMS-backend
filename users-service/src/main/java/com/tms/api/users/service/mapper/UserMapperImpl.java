package com.tms.api.users.service.mapper;

import com.tms.api.users.data.dto.UserDto;
import com.tms.api.users.data.entity.User;
import com.tms.api.users.data.model.user.CreateUserRequestModel;
import com.tms.api.users.data.model.user.UpdateUserRequestModel;
import com.tms.api.users.data.model.user.UserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

//    @Autowired
//    private ModelMapper modelMapper;
    private final static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDto entityToDto(User entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public UserDto createRequestToDto(CreateUserRequestModel createUserRequestModel) {
        return modelMapper.map(createUserRequestModel, UserDto.class);
    }

    @Override
    public UserDto updateRequestToDto(UpdateUserRequestModel updateUserRequestModel) {
        return modelMapper.map(updateUserRequestModel, UserDto.class);
    }

    @Override
    public UserResponseModel createResponseFromDto(UserDto dto){
        return modelMapper.map(dto, UserResponseModel.class);
    }

//    @Override
//    public User updateEntity(User entity, UserDto dto) {
//        ClassUtils.setIfNotNull(dto::getFirstName, entity::setFirstName);
//        ClassUtils.setIfNotNull(dto::getLastName, entity::setLastName);
//        ClassUtils.setIfNotNull(dto::getEmail, entity::setEmail);
//        ClassUtils.setIfNotNull(dto::getPassword, entity::setPassword);
//        return entity;
//    }
}
