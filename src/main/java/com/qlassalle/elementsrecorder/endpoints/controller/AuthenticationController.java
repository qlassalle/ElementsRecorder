package com.qlassalle.elementsrecorder.endpoints.controller;

import com.qlassalle.elementsrecorder.domain.exceptions.InvalidPasswordException;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.input.AuthenticationRequest;
import com.qlassalle.elementsrecorder.domain.model.input.RegistrationRequest;
import com.qlassalle.elementsrecorder.domain.model.output.AuthenticationResponse;
import com.qlassalle.elementsrecorder.infra.security.JwtUtil;
import com.qlassalle.elementsrecorder.service.AuthenticationService;
import com.qlassalle.elementsrecorder.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;
    private final AuthenticationService service;

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                                                   request.getPassword()));
        var user = userDetailsService.loadUserByUsername(request.getEmail());
        return new AuthenticationResponse(jwtUtil.generateToken(user.getUsername()));
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody @Valid RegistrationRequest request) {
        if (!request.validate()) {
            throw new InvalidPasswordException("Please provide a non-empty password and ensure it matches the confirm " +
                                               "password");
        }
        User user = service.register(request.getEmail(), request.getPassword());
        return new AuthenticationResponse(jwtUtil.generateToken(user.getEmail()));
    }
}
