package com.ui.require4testing.service;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Testcase;
import com.ui.require4testing.repository.RequirementsRepository;
import com.ui.require4testing.repository.TestcaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TestcaseService {
    @Autowired
    private final TestcaseRepository testcaseRepository;

    public List<Testcase> getAllTestcases() {
        return testcaseRepository.findAll();
    }

    public Testcase getTestcaseById(long id) {
        Testcase testcase = testcaseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return testcase;
    }

    public void save(Testcase testcase){
        testcaseRepository.save(testcase);
    }

    public void update(Testcase testcase){
        testcaseRepository.save(testcase);
    }

    public void delete(Long id){
        Testcase testcase = testcaseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        testcaseRepository.delete(testcase);
    }
}
