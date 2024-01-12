package xyz.digivice.drivemerge.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;

    @NotEmpty(message = "Please enter your full name")
    private String name;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;
}
