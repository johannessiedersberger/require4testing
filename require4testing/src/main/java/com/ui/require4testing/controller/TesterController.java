package com.ui.require4testing.controller;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Tester;
import com.ui.require4testing.service.RequirementsService;
import com.ui.require4testing.service.TesterService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("testerController")
@Getter
@Setter
@SessionScoped
public class TesterController {

    @Autowired
    private final TesterService testerService;

    private List<Tester> testers;

    private Tester tester = new Tester();

    private Tester selectedTester;

    private String name;

    final Logger logger = LoggerFactory.getLogger(RequirementsController.class);

    public TesterController(TesterService testerService) {
        this.testerService = testerService;
    }

    @PostConstruct
    public void init(){
        testers = testerService.getAllTesters();
    }

    public String createTester(){
        this.tester.setName(this.name);

        testerService.save(tester);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testers-list.xhtml?faces-redirect=true";
    }

    public String goToCreateTesterPage(){
        return "testers-form.xhtml?faces-redirect=true";
    }

    public String updateTester() {
        testerService.save(this.selectedTester);
        return "testers-list.xhtml?faces-redirect=true";
    }

    public String deleteTester(Tester tester){

        long id = tester.getTesterId();
        logger.info("" + id);
        testerService.delete(id);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();


        return "testers-list.xhtml?faces-redirect=true";
    }
}
