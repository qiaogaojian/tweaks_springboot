package com.mega.tweaks.service;

import com.mega.tweaks.model.vo.GreetingVO;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService
{
    private static final String     template = "Hello, %s!";
    private final        AtomicLong counter  = new AtomicLong();

    public GreetingVO getGreeting(String name) {
        GreetingVO greeting = new GreetingVO();
        greeting.setId(counter.incrementAndGet());
        greeting.setName(String.format(template, name));
        return greeting;
    }
}
