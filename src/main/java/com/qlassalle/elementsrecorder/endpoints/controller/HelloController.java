package com.qlassalle.elementsrecorder.endpoints.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequestMapping("/hello")
@RestController
@Slf4j
public class HelloController {

    @GetMapping
    public Map<String, String> sayHello() {
        log.info("Hit on elements-recorder-back on path /hello");
        return Map.of("Hello", "You!");
    }
}
