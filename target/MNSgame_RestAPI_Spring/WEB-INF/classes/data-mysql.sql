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
('Base de données');

INSERT INTO answer(id,content,is_correct, chained_question_id, creator_id, question_id) VALUES
(1, "api",true,null,1,null),
(2, "ape",true,null,1,null),
(3, "cinq",true,null,1,null),
(4, "Sept",true,null,1,null),
(5, "Oui",true,null,2,null),
(6, "Non",false,null,1,null),
(7, "ELO",true,null,2,null),
(8, "ELI",false,null,2,null);

INSERT INTO question(id,content, points, correct_answer_id, creator_id, theme_id) VALUES
(1, "Nommer les ...",5,8,2,1),
(3, "Combien y a-t-il de ...",10,3,1,3),
(2, "Il existe deux types de ...",7,2,1,2);

UPDATE answer SET question_id = 1, chained_question_id=2 WHERE id=1;
UPDATE answer SET question_id = 2, chained_question_id=2 WHERE id=2;
UPDATE answer SET question_id = 3, chained_question_id=3 WHERE id=3;
UPDATE answer SET question_id = 2, chained_question_id=3 WHERE id=4;
UPDATE answer SET question_id = 1 WHERE id=5;
UPDATE answer SET question_id = 3 WHERE id=6;


INSERT INTO scenario(id,name,description, is_published, creator_id, first_question_id, media_id) VALUES
(1, "Creation de site web",null,false,1,1,null),
(2, "Le framework Spring",null,true,2,3,null);

INSERT INTO game_participants(user_id,scenario_id) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(3, 2);

INSERT INTO player_responses(question_id,answer_id,user_id,scenario_id) VALUES
(1 , 1 , 3 , 2),
(2 , 2 , 3 , 2),
(3 , 6 , 3 , 2);