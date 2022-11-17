package com.cedricmtta.m2gi.transactiondemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class StudentEntity {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<ScoreEntity> scores;




















//    // Meilleure gestion de la relation
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    private Set<ScoreEntity> scores = new HashSet<>();
//
//    public void addScores(ScoreEntity... scores) {
//        Arrays.stream(scores).forEach(s -> {
//            s.setStudent(this);
//            this.scores.add(s);
//        });
//    }
}
