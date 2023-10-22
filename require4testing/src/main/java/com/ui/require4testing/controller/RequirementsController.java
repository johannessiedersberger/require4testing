package com.ui.require4testing.controller;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.service.RequirementsService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;

@Component("requirementsController")
@Getter
@Setter
@SessionScoped
public class RequirementsController {

    @Autowired
    private final RequirementsService requirementsService;

    private List<Requirement> requirements;

    private Requirement requirement = new Requirement();

    private Requirement selectedRequirement;

    private String name;
    private String description;

    final Logger logger = LoggerFactory.getLogger(RequirementsController.class);

    public RequirementsController(RequirementsService requirementsService) {
        this.requirementsService = requirementsService;
    }

    @PostConstruct
    public void init(){
        requirements = requirementsService.getAllRequirements();
    }

    public String createRequirement(){
        this.requirement.setName(this.name);
        this.requirement.setDescription(this.description);

        requirementsService.save(requirement);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "requirements-list.xhtml?faces-redirect=true";
    }

    public String goToCreateRequirementPage(){
        return "requirements-form.xhtml?faces-redirect=true";
    }

    public String updateRequirement() {
        requirementsService.save(this.selectedRequirement);
        return "requirements-list.xhtml?faces-redirect=true";
    }

    public String deleteRequirement(Requirement requirement){

        long id = requirement.getRequirementId();

        requirementsService.delete(id);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "requirements-list.xhtml?faces-redirect=true";
    }



}
