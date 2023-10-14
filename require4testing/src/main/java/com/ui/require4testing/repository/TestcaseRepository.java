package com.ui.require4testing.repository;

import com.ui.require4testing.model.Requirement;
import com.ui.require4testing.model.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestcaseRepository extends JpaRepository<Testcase, Long> {
}
