package com.ui.require4testing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Testcase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testcaseId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="requirement_id")
    private Requirement requirement;

    private String name;
    private String description;
}
