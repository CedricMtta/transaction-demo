package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.domain.ScoreEntity;
import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;

@Service
@AllArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreEntity saveMauvaiseNote(StudentEntity student) {
        var mauvaiseNote = new ScoreEntity();
        mauvaiseNote.setId(2);
        mauvaiseNote.setStudent(student);
        mauvaiseNote.setScore(0);
        return scoreRepository.save(mauvaiseNote);
    }
    
    public ScoreEntity saveMauvaiseNoteWithError(StudentEntity student) {
        var mauvaiseNote = new ScoreEntity();
        mauvaiseNote.setId(2);
        mauvaiseNote.setStudent(student);
        mauvaiseNote.setScore(0);
        scoreRepository.save(mauvaiseNote);
        throw new IllegalArgumentException("Oups, impossible de sauvegarder la mauvaise note :D");
    }

    @Transactional
    public ScoreEntity saveMauvaiseNoteWithErrorInTransaction(StudentEntity student) {
        var mauvaiseNote = new ScoreEntity();
        mauvaiseNote.setId(2);
        mauvaiseNote.setStudent(student);
        mauvaiseNote.setScore(0);
        scoreRepository.save(mauvaiseNote);
        throw new IllegalArgumentException("Oups, impossible de sauvegarder la mauvaise note :D");
    }
}
