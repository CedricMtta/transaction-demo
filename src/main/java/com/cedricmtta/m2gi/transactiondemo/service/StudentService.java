package com.cedricmtta.m2gi.transactiondemo.service;

import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentEntity save(StudentEntity student) {
        return studentRepository.save(student);
    }
}
