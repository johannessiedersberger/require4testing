package com.ui.require4testing.controller;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.service.RequirementsService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("requirementsController")
@Getter
@Setter
@AllArgsConstructor
public class RequirementsController {

    @Autowired
    private final RequirementsService requirementsService;

    private List<Requirement> requirements;

    @PostConstruct
    public void init(){
        Requirement requirement = new Requirement();

        requirements = requirementsService.getAllRequirements();
    }
}
