package com.cedricmtta.m2gi.transactiondemo;

import com.cedricmtta.m2gi.transactiondemo.domain.ScoreEntity;
import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import com.cedricmtta.m2gi.transactiondemo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class TransactionDemoApplicationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    void saveStudentAlice() {
        var alice = new StudentEntity();
        alice.setId(1);
        alice.setName("Alice");
        alice = studentService.save(alice);
        System.out.println("L etudiant est persisté en DB. A cette étape, nous pouvons le voir.");

        var mauvaiseNote = new ScoreEntity();
        mauvaiseNote.setId(2);
        mauvaiseNote.setStudent(alice);
        mauvaiseNote.setScore(0);
        scoreRepository.save(mauvaiseNote);
        studentRepository.findById(1);
    }

    @Test
    void improveAliceScore() {
        StudentEntity alice = studentRepository.findById(1).orElseThrow();
        ScoreEntity mauvaiseNote = alice.getScores().stream().findFirst().orElseThrow();
        mauvaiseNote.setScore(10);
        scoreRepository.save(mauvaiseNote);
    }

}
