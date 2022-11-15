package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotationProcessOrchestratorService {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;


}
