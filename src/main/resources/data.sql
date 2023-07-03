INSERT INTO cliente(id, cep, complemento, nome) VALUES
(1L, '6968se1', 'Complemento Endereço CLiente 1', 'Cliente 1');

INSERT INTO produto(id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1L, true, 'Produto 1', 5.0, 1L),
(2L, true, 'Produto 2', 6.6, 1L),
(3L, true, 'Produto 3', 7.0, 2L);

INSERT INTO sacola(id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 0, false, 0.0, 1L);