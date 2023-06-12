package com.mega.tweaks.api.service;

import com.mega.tweaks.api.model.vo.GreetingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService
{

    private static final String     template = "Hello, %s!";
    private final        AtomicLong counter  = new AtomicLong();

    @Autowired
    public GreetingService() {

    }

    public GreetingVO getGreeting(String name) {
        GreetingVO greeting = new GreetingVO();
        greeting.setId(counter.incrementAndGet());
        greeting.setName(String.format(template, name));
        return greeting;
    }
}