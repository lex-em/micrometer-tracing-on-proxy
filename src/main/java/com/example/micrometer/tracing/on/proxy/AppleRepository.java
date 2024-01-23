package com.example.micrometer.tracing.on.proxy;

import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppleRepository extends JpaRepository<Apple, Integer> {

    @Override
    @NewSpan
    List<Apple> findAll();
}
