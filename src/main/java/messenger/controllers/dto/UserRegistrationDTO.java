package messenger.controllers.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by flogiston on 03.06.19.
 */
@Data
public class UserRegistrationDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
}