package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.configuration.security.JwtUtil;
import com.qlassalle.elementsrecorder.model.input.AuthenticationRequest;
import com.qlassalle.elementsrecorder.model.output.AuthenticationResponse;
import com.qlassalle.elementsrecorder.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        // TODO: 09/05/2020 Add a ControllerAdvice to handle the exception here
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                                                   request.getPassword()));
        var user = userDetailsService.loadUserByUsername(request.getUsername());
        return new AuthenticationResponse(jwtUtil.generateToken(user));
    }
}
