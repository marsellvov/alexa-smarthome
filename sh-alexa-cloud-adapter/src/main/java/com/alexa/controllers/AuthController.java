package com.alexa.controllers;

import com.alexa.dto.Token;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/token")
public class AuthController {
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Token sayHello(@RequestParam(value="code", required=false) String code,
                   @RequestParam(value="client_id", required=false) String client_id,
                   @RequestParam(value="client_secret", required=false) String client_secret) {

        System.out.println("code: " + code);
        System.out.println("client_id: " + client_id);
        System.out.println("client_secret: " + client_secret);

        UUID token = UUID.randomUUID();
        return new Token(token.toString());
    }
}
