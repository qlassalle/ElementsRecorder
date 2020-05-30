package com.qlassalle.elementsrecorder.model.input;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class RegistrationRequest {
    @Email
    private String email;
    private String password;
    private String confirmPassword;

    public boolean validate() {
        return (!password.isBlank() && !password.isEmpty()) && password.equals(confirmPassword);
    }
}
