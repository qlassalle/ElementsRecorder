package com.qlassalle.elementsrecorder.endpoints.controller;

import com.qlassalle.elementsrecorder.domain.model.CustomUserDetails;
import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @GetMapping
    public List<Tag> findAll(@AuthenticationPrincipal CustomUserDetails user) {
        return service.findAll(user.getUser().getId());
    }
}
