package org.project.springweb.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.project.springweb.validation.user.Email;

@Data
public class UserLoginRequestDto {
    @NotEmpty
    @Size(min = 8, max = 20)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;
}
