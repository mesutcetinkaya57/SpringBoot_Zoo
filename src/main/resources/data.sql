INSERT INTO USERS VALUES ('user1', '{bcrypt}$2a$10$J1UN5QKWUSz1IhdwAHvJquQLtUyEguSLzqXMhgH4XL/xk7k2W.lOO', true);
INSERT INTO USERS VALUES ('user2', '{bcrypt}$2a$10$MYCrIJ9eBM.yARiIL0ytXej8u0yLwqm9Rwl61bSLNcxpgiEwAOmW2', true);
INSERT INTO USERS VALUES ('user3', '{bcrypt}$2a$10$JT5D7YmaaSdjgmle3Auqmea1xi/yOV2jDnHaT9kUPulhLcMNFDJwC', true);

INSERT INTO AUTHORITIES VALUES ('user1','ROLE_USER');
INSERT INTO AUTHORITIES VALUES ('user2','ROLE_USER');
INSERT INTO AUTHORITIES VALUES ('user2','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES ('user3','ROLE_USER');
INSERT INTO AUTHORITIES VALUES ('user3','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES ('user3','ROLE_ADMIN');

INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (1, 'Süleyman', 'Deniz');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (2, 'Neslihan', 'Asena');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (3, 'Eda', 'Akar');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (4, 'Yasin', 'Kuru');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (5, 'Çınar', 'Muz');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (6, 'Çilek', 'Su');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (7, 'Nazlı', 'Koruluk');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (8, 'Orkun', 'Torun');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (9, 'Ahmet', 'Yaz');
INSERT INTO t_zookeeper (id,first_name,last_name) VALUES (10, 'Kadir', 'Ezine');

INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (1, 'Maymun', '2005-09-07', 1);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (2, 'Orangutan', '2008-08-06', 1);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (3, 'Goril', '2001-04-17', 1);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (4, 'Aslan', '2009-03-07', 2);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (5, 'Kaplan', '2010-11-30', 2);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (6, 'Papagan', '2010-01-20', 3);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (7, 'Timsah', '2008-09-04', 5);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (8, 'Kertenkele', '2008-09-04', 5);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (9, 'Yılan', '2007-08-06', 5);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (10, 'Zürafa', '2009-02-24', 6);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (11, 'Devekuşu', '2000-03-09', 7);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (12, 'Geyik', '2000-06-24', 8);
INSERT INTO t_animal (id,name,birth_date,zookeeper_id) VALUES (13, 'Tavus Kuşu', '2002-06-08', 9);

INSERT INTO t_vet (id,first_name,last_name) VALUES (16, 'Gürkan', 'Laçin');
INSERT INTO t_vet (id,first_name,last_name) VALUES (17, 'Nuran', 'Kaçin');
INSERT INTO t_vet (id,first_name,last_name) VALUES (18, 'Yaren', 'Baçin');
