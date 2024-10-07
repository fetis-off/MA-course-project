package org.project.springweb.mapper;

import org.mapstruct.Mapper;
import org.project.springweb.config.MapperConfig;
import org.project.springweb.dto.user.UserRegistrationRequestDto;
import org.project.springweb.dto.user.UserResponseDto;
import org.project.springweb.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRegistrationRequestDto registrationRequestDto);
}
