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

    private Map<String, String> userTypes;

    private String userType = "Testmanager";

    @PostConstruct
    public void init(){
        userTypes = new HashMap<>();
        userTypes.put("Requirements Engineer", "Requirements Engineer");
        userTypes.put("Testmanager", "Testmanager");
        userTypes.put("Testfallersteller", "Testfallersteller");
        userTypes.put("Tester", "Tester");
    }

    public String login() {
        return "dashboard.xhtml?faces-redirect=true";
    }

    public void onUserTypeChange() {

    }
}
