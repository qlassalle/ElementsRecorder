package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.configuration.security.JwtUtil;
import com.qlassalle.elementsrecorder.exceptions.InvalidPasswordException;
import com.qlassalle.elementsrecorder.model.input.AuthenticationRequest;
import com.qlassalle.elementsrecorder.model.input.RegistrationRequest;
import com.qlassalle.elementsrecorder.model.output.AuthenticationResponse;
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

    @PostMapping("/")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                                                   request.getPassword()));
        var user = userDetailsService.loadUserByUsername(request.getUsername());
        return new AuthenticationResponse(jwtUtil.generateToken(user));
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody @Valid RegistrationRequest request) {
        if (!request.validate()) {
            throw new InvalidPasswordException("Please provide a non-empty password and ensure it matches the confirm " +
                                               "password");
        }
        service.register(request.getEmail(), request.getPassword());
        return new AuthenticationResponse(jwtUtil.generateToken(userDetailsService.loadUserByUsername(request.getEmail())));
    }
}
