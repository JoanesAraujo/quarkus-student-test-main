-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

INSERT INTO student (id,nome,filial) VALUES
(1,'Joanes','RE')
,(2,'Gabriel', 'SA')
,(3,'Mari','VI')
,(4,'Thati','AM')
,(5,'Jesse','POA')
,(6,'Mauro','RJ')
,(7,'Victor','CU')
,(8,'Iago','RE')
,(9,'Alex','BH')
,(10,'Andre','GO');