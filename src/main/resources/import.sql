INSERT INTO huevos (tipo_huevo, precio, created_at) VALUES('Blanco', 3.5, '2020-01-20');
INSERT INTO huevos (tipo_huevo, precio, created_at) VALUES('Rojo', 4, '2020-01-20');

INSERT INTO stocks (cantidad, huevo_id, created_at) VALUES(1000, 1, '2020-01-20');
INSERT INTO stocks (cantidad, huevo_id, created_at) VALUES(10, 2, '2020-01-20');

INSERT INTO cartones (capacidad, producto, foto, created_at) VALUES(10, 'Cartón de huevo 5x2', 'paquete-10-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(12, 'Cartón de huevo 6x2', 'paquete-12-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(14, 'Cartón de huevo 7x2', 'paquete-14-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(18, 'Cartón de huevo 6x3', 'paquete-18-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(20, 'Cartón de huevo 5x4', 'paquete-20-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(24, 'Cartón de huevo 6x4', 'paquete-24-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(28, 'Cartón de huevo 7x4', 'paquete-28-huevos.png', '2020-01-20');
INSERT INTO cartones (capacidad, producto, foto,  created_at) VALUES(32, 'Cartón de huevo 8x4', 'paquete-32-huevos.png', '2020-01-20');

INSERT INTO users (enabled, password, username) VALUES (1, '$2a$10$G7qLnrOi5uQtzoQvNaBTAOq5b2GrImyAWEDN2iMDv4cp0ZAuKoHYC', 'Luis Correa');
INSERT INTO users (enabled, password, username) VALUES (1, '$2a$10$e8hSR..DYKE/8o1hIuxFRuLBUAj8/gqrzpahF8azajZIV5AAqI9C2', 'admin');
INSERT INTO users (enabled, password, username) VALUES (1, '$2a$10$e8hSR..DYKE/8o1hIuxFRuLBUAj8/gqrzpahF8azajZIV5AAqI9C2', 'Franco');

INSERT INTO authorities (authority, user_id) VALUES ('ROLE_USER', 1);
INSERT INTO authorities (authority, user_id) VALUES ('ROLE_ADMIN', 2);
INSERT INTO authorities (authority, user_id) VALUES ('ROLE_USER', 3);