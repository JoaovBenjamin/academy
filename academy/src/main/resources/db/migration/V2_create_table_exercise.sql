CREATE TABLE exercise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    regiao_do_treino VARCHAR(255) NOT NULL
);


INSERT INTO exercise (name, regiao_do_treino) 
VALUES ('Agachamento', 'Pernas');
