package org.project.springweb.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.project.springweb.validation.user.Email;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 8, max = 20)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
