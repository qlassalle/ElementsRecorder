package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.domain.exceptions.EmailExistsException;
import com.qlassalle.elementsrecorder.domain.exceptions.InvalidPasswordException;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User register(String email, String password) {
        checkUserCanRegister(email, password);
        User user = new User(UUID.randomUUID(), email, passwordEncoder.encode(password));
        return repository.save(user);
    }

    private void checkUserCanRegister(String email, String password) {
        if (repository.findByEmail(email).isPresent()) {
            throw new EmailExistsException("There is already an existing account for the address " + email);
        }
        if(!validatePassword(password)) {
            throw new InvalidPasswordException("Your password must contain at least 8 characters, 1 uppercase " +
                                                       "character, 1 lowercase character, 1 digit, 1 special " +
                                                       "character and should not contain any whitespace");
        }
    }

    private boolean validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator(
                new LengthRule(8, 255),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()
        );
        return validator.validate(new PasswordData(password))
                        .isValid();
    }
}
