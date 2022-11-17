package com.cedricmtta.m2gi.transactiondemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
}
