package com.ui.require4testing.controller;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("loginController")
public class LoginController {
    public String login() {
        return "dashboard.xhtml?faces-redirect=true";
    }
}
