package com.ui.require4testing.repository;

import com.ui.require4testing.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementsRepository extends JpaRepository<Requirement, Long> {
}
