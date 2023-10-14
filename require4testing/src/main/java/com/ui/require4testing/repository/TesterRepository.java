package com.ui.require4testing.repository;

import com.ui.require4testing.model.Testcase;
import com.ui.require4testing.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Long> {
}