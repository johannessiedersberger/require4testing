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
public class Testrun {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testrunId;
    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tester_id")
    private Tester tester;
}
