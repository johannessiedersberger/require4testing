package com.ui.require4testing.repository;

import com.ui.require4testing.model.Testcase;
import com.ui.require4testing.model.Testrun;
import com.ui.require4testing.model.TestrunTestcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestrunTestcaseRepository extends JpaRepository<TestrunTestcase, Long> {
    Optional<TestrunTestcase> findByTestrunAndTestcase(Testrun testrun, Testcase testcase);
}

