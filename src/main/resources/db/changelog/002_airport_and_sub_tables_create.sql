CREATE TABLE country(
    id serial primary key,
    name varchar(255) not null
);
INSERT INTO country VALUES (1,'Turkey');
INSERT INTO country VALUES (2,'UNITED STATES');
INSERT INTO country VALUES (3,'FRANCE');
INSERT INTO country VALUES (4,'GERMANY');
INSERT INTO country VALUES (5,'ITALY');
INSERT INTO country VALUES (6,'SPAIN');
INSERT INTO country VALUES (7,'GREECE');
INSERT INTO country VALUES (8,'SWEDEN');
INSERT INTO country VALUES (9,'NORWAY');
INSERT INTO country VALUES (10,'CANADA');

SELECT setval('country_id_seq', (SELECT MAX(id) FROM country));

CREATE TABLE city(
    id serial primary key,
    name varchar(255) not null,
    country_id integer not null,
    CONSTRAINT country_id_fk
    FOREIGN KEY (country_id)
    REFERENCES country(id)
);


INSERT INTO city VALUES (1,'İstanbul',1);
INSERT INTO city VALUES (2,'Ankara',1);
INSERT INTO city VALUES (3,'İzmir',1);
INSERT INTO city VALUES (4,'Bursa',1);
INSERT INTO city VALUES (5,'Çanakkale',1);

INSERT INTO city VALUES (6,'New York',2);
INSERT INTO city VALUES (7,'Los Angeles',2);
INSERT INTO city VALUES (8,'Chicago',2);
INSERT INTO city VALUES (9,'Houston',2);
INSERT INTO city VALUES (10,'Philadelphia',2);

INSERT INTO city VALUES (11,'Paris',3);
INSERT INTO city VALUES (12,'Marseille',3);
INSERT INTO city VALUES (13,'Lyon',3);
INSERT INTO city VALUES (14,'Toulouse',3);
INSERT INTO city VALUES (15,'Nice',3);

INSERT INTO city VALUES (16,'Berlin',4);
INSERT INTO city VALUES (17,'Hamburg',4);
INSERT INTO city VALUES (18,'Munich',4);
INSERT INTO city VALUES (19,'Cologne',4);
INSERT INTO city VALUES (20,'Frankfurt',4);

INSERT INTO city VALUES (21,'Rome',5);
INSERT INTO city VALUES (22,'Milan',5);
INSERT INTO city VALUES (23,'Naples',5);
INSERT INTO city VALUES (24,'Turin',5);
INSERT INTO city VALUES (25,'Palermo',5);

INSERT INTO city VALUES (26,'Madrid',6);
INSERT INTO city VALUES (27,'Barcelona',6);
INSERT INTO city VALUES (28,'Valencia',6);
INSERT INTO city VALUES (29,'Seville',6);
INSERT INTO city VALUES (30,'Zaragoza',6);

INSERT INTO city VALUES (31,'Athens',7);
INSERT INTO city VALUES (32,'Thessaloniki',7);
INSERT INTO city VALUES (33,'Patras',7);
INSERT INTO city VALUES (34,'Heraklion',7);
INSERT INTO city VALUES (35,'Larissa',7);

INSERT INTO city VALUES (36,'Stockholm',8);
INSERT INTO city VALUES (37,'Gothenburg',8);
INSERT INTO city VALUES (38,'Malmö',8);
INSERT INTO city VALUES (39,'Uppsala',8);
INSERT INTO city VALUES (40,'Västerås',8);

INSERT INTO city VALUES (41,'Oslo',9);
INSERT INTO city VALUES (42,'Bergen',9);
INSERT INTO city VALUES (43,'Trondheim',9);
INSERT INTO city VALUES (44,'Stavanger',9);
INSERT INTO city VALUES (45,'Drammen',9);

INSERT INTO city VALUES (46,'Toronto',10);
INSERT INTO city VALUES (47,'Montreal',10);
INSERT INTO city VALUES (48,'Vancouver',10);
INSERT INTO city VALUES (49,'Calgary',10);
INSERT INTO city VALUES (50,'Edmonton',10);

SELECT setval('city_id_seq', (SELECT MAX(id) FROM city));

CREATE TABLE airport(
    id serial primary key,
    name varchar(255) not null,
    city_id integer not null,
    CONSTRAINT airport_id_fk
        FOREIGN KEY (city_id)
            REFERENCES city(id)
);