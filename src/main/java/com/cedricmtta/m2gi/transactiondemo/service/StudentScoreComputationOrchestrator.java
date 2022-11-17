package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentScoreComputationOrchestrator {
    private final StudentService studentService;
    private final ScoreService scoreService;
    
    @Transactional
    public void saveAliceAndMauvaiseNoteInSameTransaction() {
        var alice = studentService.saveAlice();
        scoreService.saveMauvaiseNoteWithError(alice);
    }
}
