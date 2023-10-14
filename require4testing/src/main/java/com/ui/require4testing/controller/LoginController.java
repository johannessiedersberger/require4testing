package com.ui.require4testing.controller;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component("loginController")
@Getter
@Setter
public class LoginController {
    private String username;
    private String password;



    @PostConstruct
    public void init(){
    }

    public String login() {
        return "dashboard.xhtml?faces-redirect=true";
    }


}
