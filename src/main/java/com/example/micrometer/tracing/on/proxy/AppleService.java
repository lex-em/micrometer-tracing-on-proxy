package com.example.micrometer.tracing.on.proxy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class AppleService {

    private final AppleRepository appleRepository;

    public List<Apple> getApples() {
        return appleRepository.findAll();
    }

    @PostConstruct
    public void init() {
        appleRepository.save(new Apple(1, "red delicious", 50));
    }

}
