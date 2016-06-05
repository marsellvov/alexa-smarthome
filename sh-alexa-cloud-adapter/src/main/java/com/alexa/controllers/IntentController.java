package com.alexa.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntentController {
    @RequestMapping("/service")
    String service() {
        return "Unbelievable denys delechuk, ya whoo!";
    }
}
