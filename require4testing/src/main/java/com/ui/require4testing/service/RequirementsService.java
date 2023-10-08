package com.ui.require4testing.service;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.repository.RequirementsRepository;
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
public class RequirementsService {
    @Autowired
    private final RequirementsRepository requirementsRepository;

    public List<Requirement> getAllRequirements() {
        return requirementsRepository.findAll();
    }

    public Requirement getRequirementById(long id) {
        Requirement requirement = requirementsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return requirement;
    }

    public void save(Requirement requirement){
        requirementsRepository.save(requirement);
    }

    public void update(Requirement requirement){
        requirementsRepository.save(requirement);
    }

    public void delete(Long id){
        Requirement requirement = requirementsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        requirementsRepository.delete(requirement);
    }

}
