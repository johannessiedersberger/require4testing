package com.ui.require4testing.controller;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Tester;
import com.ui.require4testing.service.TestcaseService;
import com.ui.require4testing.service.TesterService;
import com.ui.require4testing.service.TestrunService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ui.require4testing.model.Testrun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("testrunController")
@Getter
@Setter
@SessionScoped
public class TestrunController {
    @Autowired
    private final TestcaseService testcaseService;

    @Autowired
    private final TestrunService testrunService;

    @Autowired
    private final TesterService testerService;

    private List<Testrun> testruns;

    private Testrun selectedTestrun;

    private Tester tester;
    private String name;

    private Testrun testrun = new Testrun();

    private Map<String, Tester> testers;


    public TestrunController(TestcaseService testcaseService, TestrunService testrunService, TesterService testerService) {
        this.testcaseService = testcaseService;
        this.testrunService = testrunService;
        this.testerService = testerService;
    }

    @PostConstruct
    public void init(){
        testruns = testrunService.getAllTestruns();

        testers = new HashMap<>();

        List<Tester> testers = testerService.getAllTesters();

        for(Tester t : testers){
            this.testers.put(t.getTesterId() +  ": " + t.getName(), tester);
        }
    }

    public String createTestrun() {

        this.testrun.setName(this.name);
        this.testrun.setTester(this.tester);

        testrunService.save(testrun);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testruns-list.xhtml?faces-redirect=true";
    }

    public void onTesterChange() {

    }

    public String gotToCreateTestrun(){
        return "testruns-form.xhtml?faces-redirect=true";
    }
}
