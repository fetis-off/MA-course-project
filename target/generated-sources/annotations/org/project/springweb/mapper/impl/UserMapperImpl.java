package org.project.springweb.mapper.impl;

import javax.annotation.processing.Generated;
import org.project.springweb.dto.user.UserRegistrationRequestDto;
import org.project.springweb.dto.user.UserResponseDto;
import org.project.springweb.mapper.UserMapper;
import org.project.springweb.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-03T15:01:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        if ( user.getId() != null ) {
            userResponseDto.setId( user.getId() );
        }
        if ( user.getEmail() != null ) {
            userResponseDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstName() != null ) {
            userResponseDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userResponseDto.setLastName( user.getLastName() );
        }
        if ( user.getShippingAddress() != null ) {
            userResponseDto.setShippingAddress( user.getShippingAddress() );
        }

        return userResponseDto;
    }

    @Override
    public User toUser(UserRegistrationRequestDto registrationRequestDto) {
        if ( registrationRequestDto == null ) {
            return null;
        }

        User user = new User();

        if ( registrationRequestDto.getEmail() != null ) {
            user.setEmail( registrationRequestDto.getEmail() );
        }
        if ( registrationRequestDto.getPassword() != null ) {
            user.setPassword( registrationRequestDto.getPassword() );
        }
        if ( registrationRequestDto.getFirstName() != null ) {
            user.setFirstName( registrationRequestDto.getFirstName() );
        }
        if ( registrationRequestDto.getLastName() != null ) {
            user.setLastName( registrationRequestDto.getLastName() );
        }
        if ( registrationRequestDto.getShippingAddress() != null ) {
            user.setShippingAddress( registrationRequestDto.getShippingAddress() );
        }

        return user;
    }
}
