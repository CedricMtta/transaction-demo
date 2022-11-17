package com.cedricmtta.m2gi.transactiondemo;

import com.cedricmtta.m2gi.transactiondemo.domain.ScoreEntity;
import com.cedricmtta.m2gi.transactiondemo.domain.StudentEntity;
import com.cedricmtta.m2gi.transactiondemo.repository.ScoreRepository;
import com.cedricmtta.m2gi.transactiondemo.repository.StudentRepository;
import com.cedricmtta.m2gi.transactiondemo.service.ScoreService;
import com.cedricmtta.m2gi.transactiondemo.service.StudentScoreComputationOrchestrator;
import com.cedricmtta.m2gi.transactiondemo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest()
class TransactionDemoApplicationTests {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentScoreComputationOrchestrator studentScoreComputationOrchestrator;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    void cas_Normal() {
        var alice = studentService.saveAlice();
        var mauvaiseNote = scoreService.saveMauvaiseNote(alice);
    }

    @Test
    void cas_Avec_Erreur_Note() {
        var alice = studentService.saveAlice();
        var mauvaiseNote = scoreService.saveMauvaiseNoteWithError(alice);
    }

    @Test
    @Transactional
    void cas_Avec_Erreur_Note_Test_Transactionnel() {
        var alice = studentService.saveAlice();
        var mauvaiseNote = scoreService.saveMauvaiseNoteWithError(alice);
    }

    @Test
    void cas_Avec_Erreur_Note_Methodes_Transactionnelle() {
        var alice = studentService.saveAliceInTransaction();
        var mauvaiseNote = scoreService.saveMauvaiseNoteWithErrorInTransaction(alice);
    }

    @Test
    void cas_Avec_Erreur_Note_Orchestrateur_Transactionnel() {
        studentScoreComputationOrchestrator.saveAliceAndMauvaiseNoteInSameTransaction();
    }

    @Test
    void cas_relationship_deux_transactions() {
        var alice = studentService.saveAlice();
        var note = scoreService.saveMauvaiseNote(alice);
        
        // note.student = ?
        // alice.notes = ?
        System.out.println(note);

        var aliceAfterSave = studentRepository.findById(1).orElseThrow();
        // alice.notes = ?
        System.out.println(aliceAfterSave.getScores());
        
        // Réessayer avec FetchType.EAGER + explication
        // Alice l.62 != Alice l.69
    }

    // Repasser en FetchType.LAZY
    @Test
    @Transactional
    void cas_relationship_single_transactions() {
        var alice = studentService.saveAlice();
        var note = scoreService.saveMauvaiseNote(alice);

        // note.student = ?
        // alice.notes = ?
        System.out.println(note);

        var aliceAfterSave = studentRepository.findById(1).orElseThrow();
        // alice.notes = ?
        System.out.println(aliceAfterSave.getScores());
        
        // Explication du 1st level cache
    }

    @Test
    @Transactional
    void cas_relationship_correct_single_transactions() {
        var alice = studentService.saveAlice();
        var note = scoreService.saveMauvaiseNote(alice);
        alice.setScores(Set.of(note));

        // note.student = ?
        // alice.notes = ?
        System.out.println(note);

        var aliceAfterSave = studentRepository.findById(1).orElseThrow();
        // alice.notes = ?
        System.out.println(aliceAfterSave.getScores());
    }

    // Décomenter StudentEntity et ce test
//    @Test
////    @Transactional ou non, cela fonctionnera dans tout les cas
//    void cas_ideal() {
//        var alice = new StudentEntity();
//        alice.setId(1);
//        alice.setName("Alice");
//        
//        var note = new ScoreEntity();
//        note.setId(1);
//        note.setScore(10);
//        
//        alice.addScores(note);
//        
//        studentRepository.save(alice);
//    }
    
}
