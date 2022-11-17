package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentEntity saveAlice() {
        var alice = new StudentEntity();
        alice.setId(1);
        alice.setName("Alice");
        return studentRepository.save(alice);
    }

    @Transactional
    public StudentEntity saveAliceInTransaction() {
        var alice = new StudentEntity();
        alice.setId(1);
        alice.setName("Alice");
        return studentRepository.save(alice);
    }
}
