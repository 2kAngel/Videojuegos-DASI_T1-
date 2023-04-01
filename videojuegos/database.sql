DROP TABLE VIDEOJUEGOS;
DROP TABLE DESARROLLADORAS;

CREATE TABLE VIDEOJUEGOS
(
    ID VARCHAR(9),
    NOMBRE VARCHAR(100),
    FECHA_LANZAMIENTO DATE,
    GENERO VARCHAR(100),
    CARTEL VARCHAR(200),

    CONSTRAINT "PK_VIDEOJUEGOS" PRIMARY KEY (ID),
    CONSTRAINT "NN_NOMBRE" CHECK ( NOMBRE IS NOT NULL ),
    CONSTRAINT "NN_FECHA_LANZAMIENTO" CHECK ( FECHA_LANZAMIENTO IS NOT NULL ),
    CONSTRAINT "NN_GENERO" CHECK ( GENERO IS NOT NULL )
);

CREATE TABLE DESARROLLADORAS
(
    ID VARCHAR(9),
    NOMBRE VARCHAR(100),
    NACIONALIDAD VARCHAR(100),
    FECHA_CREACION DATE,
    FOTO VARCHAR(200),

        CONSTRAINT "PK_PERSONAJES" PRIMARY KEY (ID),
        CONSTRAINT "NN_NOMBRE" CHECK ( NOMBRE IS NOT NULL ),
        CONSTRAINT "NN_NACIONALIDAD" CHECK ( NACIONALIDAD IS NOT NULL )
);



CREATE SEQUENCE seq_videojuegos MINVALUE 1 MAXVALUE 999999998 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_personajes MINVALUE 1 MAXVALUE 999999998 START WITH 1 INCREMENT BY 1;



INSERT INTO VIDEOJUEGOS(ID,          NOMBRE,          FECHA_LANZAMIENTO, GENERO, CARTEL)
                        VALUES('1', 'Assassin´s Creed 2', to_date('19-08-2009', 'dd-mm-yyyy'), 'Acción-aventura', 'https://cdn1.epicgames.com/salesEvent/salesEvent/AC2_GameName_Store_Portrait_1200x1600_1200x1600-2c5e000213988c5dde375bb2602e9986') ;

INSERT INTO VIDEOJUEGOS(ID,          NOMBRE,          FECHA_LANZAMIENTO, GENERO, CARTEL)
                        VALUES('2', 'Assassin´s Creed: Brotherhood', to_date('16-08-2010', 'dd-mm-yyyy'), 'Acción-aventura', 'https://store.ubi.com/dw/image/v2/ABBS_PRD/on/demandware.static/-/Sites-masterCatalog/default/dw634ad17f/images/large/56c4948088a7e300458b46b6.jpg?sw=341&sh=450&sm=fit') ;

INSERT INTO VIDEOJUEGOS(ID,          NOMBRE,          FECHA_LANZAMIENTO, GENERO, CARTEL)
                        VALUES('3', 'War Thunder', to_date('01-08-2012', 'dd-mm-yyyy'), 'Simulador', 'https://cdn.mobygames.com/covers/8024149-war-thunder-xbox-one-front-cover.jpg') ;

INSERT INTO VIDEOJUEGOS(ID,          NOMBRE,          FECHA_LANZAMIENTO, GENERO, CARTEL)
                        VALUES('4', 'Call Of Duty: Black Ops II', to_date('12-08-2012', 'dd-mm-yyyy'), 'Shooter en 1ª persona', 'https://cdn-products.eneba.com/resized-products/l1tJkUk_350x200_1x-0.jpg') ;



INSERT INTO DESARROLLADORAS(ID,     NOMBRE,     NACIONALIDAD,       FECHA_CREACION,     FOTO)
                            VALUES ('1', 'UBISOFT', 'Francia', to_date('28-04-1986', 'dd-mm-yyyy'), 'https://static.wikia.nocookie.net/farcry/images/8/8c/Ubisoft.png/revision/latest?cb=20121116172010&path-prefix=es');

INSERT INTO DESARROLLADORAS(ID,     NOMBRE,     NACIONALIDAD,       FECHA_CREACION,     FOTO)
                            VALUES ('2', 'Gaijin Entertainment', 'Hungría', to_date('12-06-2002', 'dd-mm-yyyy'), 'https://avatars.githubusercontent.com/u/46653612?s=280&v=4');

INSERT INTO DESARROLLADORAS(ID,     NOMBRE,     NACIONALIDAD,       FECHA_CREACION,     FOTO)
                            VALUES ('3', 'Activision', 'Francia', to_date('01-07-1979', 'dd-mm-yyyy'), 'https://static.wikia.nocookie.net/prototype/images/f/f4/Activision.jpg/revision/latest?cb=20120403172713&path-prefix=es');
