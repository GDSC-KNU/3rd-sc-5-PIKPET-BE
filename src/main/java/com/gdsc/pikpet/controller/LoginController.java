package com.gdsc.pikpet.controller;


import com.gdsc.pikpet.config.security.UserSecurityDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/loginPage")
public class LoginController {
    @RequestMapping("/loginSuccess")
    public UserSecurityDto loginSuccess(Authentication authentication) {
        UserSecurityDto userSecurityDto = (UserSecurityDto) authentication.getPrincipal();

        return userSecurityDto;
    }

    @RequestMapping("/loginFail")
    public String loginFail() {
        return "loginFail";
    }

    @RequestMapping("/formLogin")
    public ModelAndView formLogin(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formLogin");
        return modelAndView;
    }
}
