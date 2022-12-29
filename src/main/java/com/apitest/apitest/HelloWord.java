package com.apitest.apitest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello World";
    }
}
