INSERT INTO tb_categoria (name) VALUES ('Vidrarias');
INSERT INTO tb_categoria (name) VALUES ('Equipamentos');
INSERT INTO tb_categoria (name) VALUES ('Limpeza');
INSERT INTO tb_categoria (name) VALUES ('Biológicos');
INSERT INTO tb_categoria (name) VALUES ('Bebidas');
INSERT INTO tb_categoria (name) VALUES ('Inflamáveis');

INSERT INTO tb_produto (name, description, categoria_id) VALUES ('Detergente em Pó', 'Substância química que apresentam cadeias constituídas por átomos de carbono e hidrogênio.', 3);
INSERT INTO tb_produto (name, description, categoria_id) VALUES ('Refrigerante', 'Bebida carbonatada e não alcoólica.', 5);

INSERT INTO tb_composto (name, description, produto_id) VALUES ('Tensoativo aniônico', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Enzimas', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Alvejante', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Bloqueadores ópticos', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Fragrâncias', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Corantes', 'Ele age por oxidação, redução ou ação enzimática.', 1);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Agentes sequestrantes e quelantes', 'Ele age por oxidação, redução ou ação enzimática.', 1);

INSERT INTO tb_composto (name, description, produto_id) VALUES ('Água', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Concentrados', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Carbonatos e bicarbonatos', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Sulfatos e fenóis', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Açúcar', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Acidulante', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Antioxidante', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);
INSERT INTO tb_composto (name, description, produto_id) VALUES ('Conservante', 'São misturas de extratos, óleos e destilados de frutas ou vegetais.', 2);

INSERT INTO tb_material (name, description, categoria_id) VALUES ('Balão de destilação', 'Utilizado para aquecimento de uma mistura e separação dos compostos mais voláteis.', 1);
INSERT INTO tb_material (name, description, categoria_id) VALUES ('Bastão de vidro', 'Utilizado para homogeneizar ou agitar soluções em atividades rotineiras de laboratório.', 1);
INSERT INTO tb_material (name, description, categoria_id) VALUES ('Dessecador', 'Utilizado para remover a umidade dos materiais pela presença de agentes secantes.', 1);
INSERT INTO tb_material (name, description, categoria_id) VALUES ('Barra magnética', 'Utilizado para remover a umidade dos materiais pela presença de agentes secantes.', 2);
INSERT INTO tb_material (name, description, categoria_id) VALUES ('Deionizador de água', 'Utilizado para remover íons na água, como cálcio e magnésio.', 2);
INSERT INTO tb_material (name, description, categoria_id) VALUES ('Capela de exaustão', 'Utilizada como barreira física para manipular materiais perigosos.', 2);
