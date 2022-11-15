package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.domain.ScoreEntity;
import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreEntity save(ScoreEntity score) {
        return scoreRepository.save(score);
    }
}
