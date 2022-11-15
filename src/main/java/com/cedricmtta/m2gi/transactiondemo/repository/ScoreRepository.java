package com.cedricmtta.m2gi.transactiondemo.repository;

import com.cedricmtta.m2gi.transactiondemo.domain.ScoreEntity;
import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
}
