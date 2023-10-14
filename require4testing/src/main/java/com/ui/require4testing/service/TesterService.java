package com.ui.require4testing.service;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Tester;
import com.ui.require4testing.repository.TesterRepository;
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
public class TesterService {
    @Autowired
    private final TesterRepository testerRepository;

    public List<Tester> getAllTesters() {
        return testerRepository.findAll();
    }

    public Tester getTesterById(long id) {
        Tester tester = testerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return tester;
    }

    public void save(Tester tester){
        testerRepository.save(tester);
    }

    public void update(Tester tester){
        testerRepository.save(tester);
    }

    public void delete(Long id){
        Tester tester = testerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        testerRepository.delete(tester);
    }
}
