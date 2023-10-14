package com.ui.require4testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestrunTestcase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testrunTestcaseId;

    @ManyToOne
    @JoinColumn(name = "testrun_id")
    private Testrun testrun;

    @ManyToOne
    @JoinColumn(name = "testcase_id")
    private Testcase testcase;

    @Enumerated(EnumType.STRING)
    private TestcaseStatus testcaseStatus;
}
