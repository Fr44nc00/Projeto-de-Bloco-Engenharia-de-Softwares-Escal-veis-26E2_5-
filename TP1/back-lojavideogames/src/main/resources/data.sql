-- Usuários
INSERT INTO usuario (nome, email, tipo, senha) VALUES
                                                       ('João Silva', 'joao@teste.com', 'CLIENTE', '1234'),
                                                       ('Maria Souza', 'maria@teste.com', 'FUNCIONARIO', 'abcd'),
                                                       ('Carlos Pereira', 'carlos@teste.com', 'CLIENTE', 'senha123');

-- Jogos
INSERT INTO jogo (titulo, plataforma, preco) VALUES
                                                     ('The Legend of Zelda', 'Nintendo Switch', 299.90),
                                                     ('God of War', 'PlayStation 5', 349.90),
                                                     ('Halo Infinite', 'Xbox Series X', 279.90);

-- Compras
INSERT INTO compra (usuario_id, data_hora, valor_total) VALUES
                                                                (1, CURRENT_TIMESTAMP, 649.80),
                                                                (3, CURRENT_TIMESTAMP, 299.90);

-- Relação Compra-Jogo (tabela intermediária gerada pelo @ManyToMany)
INSERT INTO compra_jogos (compra_id, jogos_id) VALUES
                                                   (1, 1),
                                                   (1, 2),
                                                   (2, 3);
