package com.ui.require4testing.controller;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Testcase;
import com.ui.require4testing.service.RequirementsService;
import com.ui.require4testing.service.TestcaseService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("testcasesController")
@Getter
@Setter
@SessionScoped
public class TestcasesController {

    @Autowired
    private final TestcaseService testcaseService;

    @Autowired
    private final RequirementsService requirementsService;

    private List<Testcase> testcases;

    private Map<Long, Long> requirements;

    private Long requirementId;
    private String name;
    private String description;

    private Testcase testcase = new Testcase();

    private Testcase selectedTestcase;

    final Logger logger = LoggerFactory.getLogger(RequirementsController.class);

    public TestcasesController(TestcaseService testcaseService, RequirementsService requirementsService) {
        this.testcaseService = testcaseService;
        this.requirementsService = requirementsService;
    }



    @PostConstruct
    public void init(){
        requirements = new HashMap<>();

        List<Requirement> requirements = requirementsService.getAllRequirements();

        for(Requirement r : requirements){
            this.requirements.put(r.getRequirementId(), r.getRequirementId());
        }

        testcases = testcaseService.getAllTestcases();
    }

    public void onRequirementChange() {
        logger.info("" + this.requirementId);
    }

    public void onRequirementChangeUpdate() {

    }

    public String createTestcase1() {
        Requirement r = requirementsService.getRequirementById(this.requirementId);

        logger.info("" + r.getRequirementId());

        this.testcase.setName(this.name);
        this.testcase.setDescription(this.description);
        this.testcase.setRequirement(r);

        testcaseService.save(testcase);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testcases-list.xhtml?faces-redirect=true";
    }

    public String deleteTestcase(Testcase testcase){
        long id = testcase.getTestcaseId();
        logger.info("" + id);
        testcaseService.delete(id);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testcases-list.xhtml?faces-redirect=true";
    }

    public String goToCreateTestCasePage(){
        return "testcases-form.xhtml?faces-redirect=true";
    }

    public String updateTestcase(){
        testcaseService.save(this.selectedTestcase);
        return "testcases-list.xhtml?faces-redirect=true";
    }
}
