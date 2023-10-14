package com.ui.require4testing.repository;

import com.ui.require4testing.model.Tester;
import com.ui.require4testing.model.Testrun;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestrunRepository extends JpaRepository<Testrun, Long> {
}
