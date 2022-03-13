package com.qlassalle.elementsrecorder.endpoints.controller;

import com.qlassalle.elementsrecorder.domain.model.Resource;
import com.qlassalle.elementsrecorder.domain.model.CustomUserDetails;
import com.qlassalle.elementsrecorder.domain.model.input.ResourceCreationRequest;
import com.qlassalle.elementsrecorder.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public List<Resource> findAll(@AuthenticationPrincipal CustomUserDetails user) {
        return resourceService.findAll(user.getUser().getId());
    }

    @GetMapping("/{id}")
    public Resource get(@PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails user) {
        return resourceService.findById(id, user.getUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource create(@RequestBody @Valid ResourceCreationRequest resourceCreationRequest,
                           @AuthenticationPrincipal CustomUserDetails user) {
        return resourceService.create(resourceCreationRequest, user.getUser().getId());
    }

//    @PutMapping("/{id}")
//    public Resource update(@PathVariable Long id, @RequestBody Resource resource,
//                          @AuthenticationPrincipal CustomUserDetails user) {
//        return resourceService.update(id, resource, user.getUser());
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails user) {
        resourceService.delete(id, user.getUser());
    }
}
