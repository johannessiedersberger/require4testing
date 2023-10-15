package com.ui.require4testing.controller;

import com.ui.require4testing.model.*;
import com.ui.require4testing.service.TestcaseService;
import com.ui.require4testing.service.TesterService;
import com.ui.require4testing.service.TestrunService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Long testerId;
    private String name;

    private Testrun testrun = new Testrun();

    private Map<String, Long> testers;

    // Edit
    private Map<String, Long> testcases;
    private Long selectedTestcase;

    private List<Testcase> selectedTestcases;


    final Logger logger = LoggerFactory.getLogger(RequirementsController.class);

    public TestrunController(TestcaseService testcaseService, TestrunService testrunService, TesterService testerService) {
        this.testcaseService = testcaseService;
        this.testrunService = testrunService;
        this.testerService = testerService;
    }

    @PostConstruct
    public void init(){
        testruns = testrunService.getAllTestruns();

        selectedTestcases = new ArrayList<>();

        testers = new HashMap<>();


        List<Tester> testers = testerService.getAllTesters();

        for(Tester t : testers){
            this.testers.put(t.getName(), t.getTesterId());
        }

        this.testcases = new HashMap<>();

        List <Testcase> testcases = testcaseService.getAllTestcases();


        for(Testcase tc : testcases){
            this.testcases.put(tc.getName(), tc.getTestcaseId());
        }
    }

    public String createTestrun() {

        this.testrun.setName(this.name);
        Tester t = testerService.getTesterById(this.testerId);
        this.testrun.setTester(t);

        testrunService.save(testrun);

        // Testcases
        for(Testcase currentTC : this.selectedTestcases){
            testrunService.connectTestrunAndTestcase(testrun, currentTC);
        }

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testruns-list.xhtml?faces-redirect=true";
    }

    public String deleteTestrun(Testrun testrun){
        long id = testrun.getTestrunId();

        // TODO: DELETE REFERENCES
        List <Testcase> testcasesList = testrunService.getTestcasesFromTestRun(testrun);
        for(Testcase tc :  testcasesList){
            testrunService.deleteConnectionTestrunTestcase(testrun, tc);
        }

        testrunService.delete(id);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "testruns-list.xhtml?faces-redirect=true";
    }

    public String updateTestrun(){
        Tester t = testerService.getTesterById(this.selectedTestrun.getTester().getTesterId());
        testrunService.save(this.selectedTestrun);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "testruns-list.xhtml?faces-redirect=true";
    }

    public void onTesterChange() {

    }

    public String gotToCreateTestrun(){
        return "testruns-form.xhtml?faces-redirect=true";
    }

    public void addTestcaseToTestRun(){

        Testcase testcaseToAdd = testcaseService.getTestcaseById(this.selectedTestcase);
        this.selectedTestcases.add(testcaseToAdd);

        logger.info("added" + testcaseToAdd.getTestcaseId());
    }

    public void deleteTestcase(Testcase testcase){
        this.selectedTestcases.remove(testcase);
        logger.info("deleted" + testcase.getTestcaseId());
    }
}
