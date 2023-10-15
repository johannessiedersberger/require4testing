package com.ui.require4testing.service;

import com.ui.require4testing.model.*;
import com.ui.require4testing.repository.TestrunRepository;
import com.ui.require4testing.repository.TestrunTestcaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TestrunService {
    @Autowired
    private final TestrunRepository testrunRepository;

    @Autowired
    private final TestrunTestcaseRepository testrunTestcaseRepository;

    public List<Testrun> getAllTestruns(){
        return testrunRepository.findAll();
    }

    public Testrun getTestrunById(Long id){
        Testrun testrun = testrunRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return testrun;
    }

    public void save(Testrun testrun){
        testrunRepository.save(testrun);
    }

    public void delete(Long id){
        Testrun testrun = testrunRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        testrunRepository.delete(testrun);
    }

    public void connectTestrunAndTestcase(Testrun testrun, Testcase testcase){
        TestrunTestcase testrunTestcase = new TestrunTestcase();
        testrunTestcase.setTestrun(testrun);
        testrunTestcase.setTestcase(testcase);
        testrunTestcase.setTestcaseStatus(TestcaseStatus.NOTEXECUTED);

        testrunTestcaseRepository.save(testrunTestcase);
    }

    public void deleteConnectionTestrunTestcase(Testrun testrun, Testcase testcase){
        TestrunTestcase testrunTestcase = testrunTestcaseRepository.findByTestrunAndTestcase(testrun, testcase).orElseThrow(EntityNotFoundException::new);
        testrunTestcaseRepository.delete(testrunTestcase);
    }

    public void updateTestrunTestcaseStatus(Testrun testrun, Testcase testcase, TestcaseStatus testcaseStatus) {
        TestrunTestcase testrunTestcase = testrunTestcaseRepository.findByTestrunAndTestcase(testrun, testcase).orElseThrow(EntityNotFoundException::new);
        testrunTestcase.setTestcaseStatus(testcaseStatus);
        testrunTestcaseRepository.save(testrunTestcase);
    }

    public List<Testcase> getTestcasesFromTestRun(Testrun testrun){
        List <TestrunTestcase> tt = testrunTestcaseRepository.findByTestrun(testrun);

        List<Testcase> tcs = new ArrayList<>();

        for(TestrunTestcase currentRunTC : tt){
            tcs.add(currentRunTC.getTestcase());
        }

        return tcs;
    }
}
