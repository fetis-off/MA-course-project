package org.project.springweb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.user.UserLoginRequestDto;
import org.project.springweb.dto.user.UserLoginResponseDto;
import org.project.springweb.dto.user.UserRegistrationRequestDto;
import org.project.springweb.dto.user.UserResponseDto;
import org.project.springweb.exception.RegistrationException;
import org.project.springweb.security.AuthenticationService;
import org.project.springweb.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register new user")
    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @Operation(summary = "Login in")
    @PostMapping("/login")
    public UserLoginResponseDto loginUser(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
