package com.cedricmtta.m2gi.transactiondemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
public class ScoreEntity {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private int score;

    @ManyToOne
    private StudentEntity student;
}
