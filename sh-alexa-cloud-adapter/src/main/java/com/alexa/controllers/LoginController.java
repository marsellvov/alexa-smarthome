package com.alexa.controllers;

import com.alexa.dto.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class LoginController {

    @Value("${amazon.login.redirecturl}")
    private String redirectUrl;


    @RequestMapping("/login")
    String login(Model model,
                 @RequestParam(name = "state", required = true) String state,
                 @RequestParam(name = "client_id", required = false) String client_id,
                 @RequestParam(name = "response_type", required = false) String response_type,
                 @RequestParam(name = "scope", required = false) String scope) {
        model.addAttribute("login", new Login(state));
        model.addAttribute("state", state);

        System.out.println("state :: " + state);
        System.out.println("client_id :: " + client_id);
        System.out.println("response_type :: " + response_type);
        System.out.println("scope :: " + scope);
        System.out.println();
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView greetingSubmit(@ModelAttribute Login login, Model model) {
        System.out.println(login.getUsername() + " :: " + login.getPassword());
        System.out.println("State :: " + login.getState());

        UUID code = UUID.randomUUID();
        String strCode = code.toString().replace("-","");

        String completeRedirectUrl = redirectUrl + "state=" + login.getState() + "&code=" + strCode;
        System.out.println("completeRedirectUrl" + completeRedirectUrl);
        ModelAndView mv = new ModelAndView("redirect:" + completeRedirectUrl);
        return mv;
    }

    @RequestMapping("/policy")
    String policy() {
        return "policy";
    }


}
