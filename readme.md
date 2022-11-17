# Transaction demo

## Demonstration SQL "pure"

Console 1:

```sql
-- Exemple 1
-- Deux opérations, la deuxieme créant une erreur
-- La premiere reste persistée
INSERT INTO student_entity(id, name)
VALUES (999, 'Bob with typo');

INSERT INTO student_entity(id, name)
VALUES (999, 'Bob');

--
TRUNCATE TABLE student_entity CASCADE;
--

-- Exemple 2
-- Wrappé dans une transaction, si une erreur survient, elle est rollbacké
-- Toutes les opérations sont annulées
BEGIN;

INSERT INTO student_entity(id, name)
VALUES (999, 'Bob with typo');

INSERT INTO student_entity(id, name)
VALUES (999, 'Bob');

COMMIT;

-- Exemple 3
-- Tant qu'une transaction n'est pas commité,
-- les données ne sont pas visible par les autres transactions
BEGIN;
INSERT INTO student_entity(id, name)
VALUES (999, 'Bob with typo');
-- Faire un select à cette étape dans une autre transaction
COMMIT;
--
TRUNCATE TABLE student_entity CASCADE;
--
-- Exemple 4
-- On peut décider de rollbacker une transaction programmatiquement
BEGIN;
INSERT INTO student_entity(id, name)
VALUES (999, 'Bob with typo');
ROLLBACK;


-- Exemple 5
-- Il existe plusieurs mode de transaction. Le défaut est "READ_COMMITED"
-- Le comportement est celui expliqué jusqu'à présent, i.e: une transaction 
-- voit les données uniquments après qu'elles soient commitées par les autres transactions
-- Repeatable Read:
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT *
FROM student_entity;
SELECT pg_sleep(10);
-- Dans une autre transaction, faire:
-- INSERT INTO student_entity(id, name)
-- VALUES(999, 'Bob with typo');
SELECT *
FROM student_entity;
COMMIT;
```

Console 2:

```sql
SELECT *
FROM student_entity
         LEFT JOIN score_entity se on student_entity.id = se.student_id;

TRUNCATE TABLE student_entity CASCADE;

INSERT INTO student_entity(id, name)
VALUES (999, 'Bob with typo');
```

## Demonstration Hibernate, Spring@Transactional, Hibernate 1st level cache

Voir `TransactionDemoApplicationTests.java`.
