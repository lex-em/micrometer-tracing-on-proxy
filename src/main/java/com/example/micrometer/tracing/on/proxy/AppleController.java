package com.example.micrometer.tracing.on.proxy;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping( "/")
public class AppleController {

    private final AppleService appleService;

    @GetMapping("/apple")
    public List<Apple> getApples() {
        return appleService.getApples();
    }
}
