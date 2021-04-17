package com.qlassalle.elementsrecorder.domain.model.input;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class RegistrationRequest {
    @Email(message = "The email '${validatedValue}' is not correctly formatted")
    private String email;
    private String password;
    private String confirmPassword;

    public boolean validate() {
        return (!password.isBlank() && !password.isEmpty()) && password.equals(confirmPassword);
    }
}
