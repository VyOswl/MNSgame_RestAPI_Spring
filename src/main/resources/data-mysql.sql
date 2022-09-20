INSERT INTO title (name) VALUES
('Admin'),
('Intervennant'),
('Stagiaire'),
('Candidat'),
('Colaborateur'),
('MNS');

INSERT INTO `user`(number_token, lastname,firstname, email, password,title_id) VALUES
(1, "ELBOUKHLIKI", "Sara", "sara@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",1),
(1, "PHAN", "Vy", "vy@mail.com","$2y$10$HeoRCKRUuRm62YcEUMWAoeIDlWYIAYrgW0ns.bvq3Gouvvtnf9PWK",1),
(1, "DOE", "Dona", "dona@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",2),
(1, "BOE", "Barry", "barry@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",2),
(1, "FOE", "Fiona", "fiona@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",5),
(1, "QUIRIN", "Franck", "franck@mail.com","$2y$10$b.QMG9m2Y/zTNmVMq2ws9OiMZ7YHW/o38jFjgYiPUohaBN5Mmlx/u",2), /*mockAccount*/
(1, "KOE", "Kyle", "kyle@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",2),
(1, "LOE", "Lola", "lola@gmail.fr","$2a$10$0CgB0SdMEg0YMamgOUlod.4YBBhkgw5vtYUlubciRHz1EKirinHt6",5);

INSERT INTO creator(id) VALUES(1);
INSERT INTO creator(id) VALUES(2);
INSERT INTO creator(id) VALUES(6);

INSERT INTO role (id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_CREATOR'),
(3, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES
(1,1),
(1,2),
(1,3),
(2,1),
(2,2),
(2,3),
(6,1),
(6,2),
(6,3);

INSERT INTO theme(name) VALUES
('Java'),
('C-sharp'),
('Développement web'),
('Développement logiciel'),
('Javascript'),
('Framework Spring'),
('HTML - CSS'),
('Base de données'),
('Autres ...');

INSERT INTO answer(id,content,is_correct, chained_question_id, creator_id, question_id) VALUES
(1, "Transport",true,null,1,null),
(2, "Session",false,null,2,null),

(3, "INNER TABLE",false,null,1,null),
(4, "ALTER TABLE",true,null,1,null),

(5, "Sept",true,null,1,null),
(6, "Cinq",false,null,1,null),

(7, "Programmation orientée objet",true,null,2,null),
(8, "Programme objet off",false,null,2,null),

(9, "Application Programming International ", false,null,2,null),
(10,"Application Programming Interface", true,null,2,null),

(11,"mapping objet-relationnel", true,null,2,null),
(12,"mapping orienté robot ", false,null,2,null),

(13,"IDE", false,null,2,null),
(14,"framework", true,null,2,null),

(15,"Structured Query Language", true,null,2,null),
(16,"Server Query Language", false,null,2,null),

(17,"Faux", true,null,2,null),
(18,"Vrai", false,null,2,null),

(19,"World Wider Web", false,null,2,null),
(20,"World Wide Web", true,null,2,null),

(21,"L'adresse MAC", true,null,2,null),
(22,"L'adresse IP", false,null,2,null),

(23,"8 bits", true,null,2,null),
(24,"1 byte", false,null,2,null),

(25,"Void", false,null,2,null),
(26,"il n'a pas de retour", true,null,2,null),

(27,"S'assurer de la disponibilité des dépendances", false,null,2,null),
(28,"Exécuter les programmes", true,null,2,null),

(29,"Vrai", true,null,2,null),
(30,"Faux", false,null,2,null),

(31,"Vrai", true,null,2,null),
(32,"Faux", false,null,2,null),

(33,"Vrai", false,null,2,null),
(34,"Faux", true,null,2,null),

(35,"Le polyphormisme", true,null,2,null),
(36,"L'Héritage", false,null,2,null),

(37,"L'encapsulation", true,null,2,null),
(38,"L'héritage", false,null,2,null),

(39,"12", false,null,2,null),
(40,"8", true,null,2,null);


INSERT INTO question(id,content, points,correct_answer_id, creator_id, theme_id) VALUES
(1, "Quelle est la 4ème couche du modèle OSI ?",5,1,1,9),
(2, "Quelle instruction SQL permet d'ajouter une colonne à une table ?",5,4,2,8),
(3, "Combien y a-t-il de couches dans le modèle OSI ?",5,5,1,9),
(4, "Que signifie POO ?",5,7,1,9),
(5, "Que signifie API ?",5,10,1,9),
(6, "SQL signifie ... ?",5,15,1,8),
(7, "ORM signifie :",5,11,2,9),
(8, "Spring boot est un ... ?",5,14,1,1),
(9, "Une classe peut hériter d'une classe de type final.",5,17,1,9),
(10, "Que signifie les trois www ?",5,20,1,9),
(11, "Quelle adresse permet d'identifier un appareil ?",5,21,1,9),
(12, "1 octet correspond à ... ?",5,23,1,9),
(13, "Quel est le type de retour d'un constructeur ?",5,26,1,1),
(14, "Quel est le rôle de la JVM?",5,28,1,1),
(15, "La visibilité protected d'un membre d'une classe permet son accès aux classes du même package?",5,29,1,1),
(16, "Une classe abstraite est une classe qui ne peut pas être instanciée.",5,31,1,1),
(17, "Une classe ne peut implémenter qu'une seule interface.",5,34,1,1),
(18, "Mécanisme permettant de modifier le comportement d'une classe fille par rapport à sa classe mère. Je suis ...?",5,35,1,1),
(19, "Principe de limitation d'accès aux attributs d'une classe : ",5,37,1,1),
(20, "(6*7+15*2)/9",5,40,1,9);

UPDATE answer SET question_id = 1, chained_question_id=2 WHERE id=1;
UPDATE answer SET question_id = 1, chained_question_id=2 WHERE id=2;

UPDATE answer SET question_id = 2, chained_question_id=3 WHERE id=3;
UPDATE answer SET question_id = 2, chained_question_id=3 WHERE id=4;

UPDATE answer SET question_id = 3, chained_question_id=4 WHERE id=5;
UPDATE answer SET question_id = 3, chained_question_id=4 WHERE id=6;

UPDATE answer SET question_id = 4, chained_question_id=5 WHERE id=7;
UPDATE answer SET question_id = 4, chained_question_id=5 WHERE id=8;

UPDATE answer SET question_id = 5, chained_question_id=null WHERE id=9;
UPDATE answer SET question_id = 5, chained_question_id=null WHERE id=10;

UPDATE answer SET question_id = 6, chained_question_id=7 WHERE id=15;
UPDATE answer SET question_id = 6, chained_question_id=7 WHERE id=16;

UPDATE answer SET question_id = 7, chained_question_id=8 WHERE id=11;
UPDATE answer SET question_id = 7, chained_question_id=8 WHERE id=12;

UPDATE answer SET question_id = 8, chained_question_id=9 WHERE id=13;
UPDATE answer SET question_id = 8, chained_question_id=9 WHERE id=14;

UPDATE answer SET question_id = 9, chained_question_id=10 WHERE id=17;
UPDATE answer SET question_id = 9, chained_question_id=10 WHERE id=18;

UPDATE answer SET question_id = 10, chained_question_id=11 WHERE id=19;
UPDATE answer SET question_id = 10, chained_question_id=11 WHERE id=20;

UPDATE answer SET question_id = 11, chained_question_id=12 WHERE id=21;
UPDATE answer SET question_id = 11, chained_question_id=12 WHERE id=22;

UPDATE answer SET question_id = 12, chained_question_id=13 WHERE id=23;
UPDATE answer SET question_id = 12, chained_question_id=13 WHERE id=24;

UPDATE answer SET question_id = 13, chained_question_id=14 WHERE id=25;
UPDATE answer SET question_id = 13, chained_question_id=14 WHERE id=26;

UPDATE answer SET question_id = 14, chained_question_id=15 WHERE id=27;
UPDATE answer SET question_id = 14, chained_question_id=15 WHERE id=28;

UPDATE answer SET question_id = 15, chained_question_id=16 WHERE id=29;
UPDATE answer SET question_id = 15, chained_question_id=16 WHERE id=30;

UPDATE answer SET question_id = 16, chained_question_id=17 WHERE id=31;
UPDATE answer SET question_id = 16, chained_question_id=17 WHERE id=32;

UPDATE answer SET question_id = 17, chained_question_id=18 WHERE id=33;
UPDATE answer SET question_id = 17, chained_question_id=18 WHERE id=34;

UPDATE answer SET question_id = 18, chained_question_id=19 WHERE id=35;
UPDATE answer SET question_id = 18, chained_question_id=19 WHERE id=36;

UPDATE answer SET question_id = 19, chained_question_id=20 WHERE id=37;
UPDATE answer SET question_id = 19, chained_question_id=20 WHERE id=38;

UPDATE answer SET question_id = 20, chained_question_id=null WHERE id=39;
UPDATE answer SET question_id = 20, chained_question_id=null WHERE id=40;


INSERT INTO scenario(id, name, description, is_published, creator_id, first_question_id, media_id) VALUES
(1, "Général","Lorem Ipsum is simply dummy text of the printing and typesetting industry.Contrary to popular belief, it's not simply random text",false,1,1,null),
(2, "Java", "Le framework Spring",true,2,3,null),
(3, "Web basic", "Creation de site web",false,1,1,null);

--INSERT INTO player_responses(question_id,answer_id,user_id,scenario_id) VALUES
--(1 , 1 , 3 , 2);


INSERT INTO game_participants(user_id,scenario_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(2, 2),
(3, 2);

INSERT INTO player_responses(question_id,answer_id,user_id,scenario_id) VALUES
(1 , 1 , 3 , 2),
(2 , 2 , 3 , 2),
(3 , 6 , 3 , 2);