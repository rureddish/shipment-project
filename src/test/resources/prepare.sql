create table CLIENT
(
    ID               INTEGER identity
        constraint CLIENT_PK
            primary key,
    CLIENT_NAME      VARCHAR(255) not null,
    REFERENCE_PERSON VARCHAR(255) not null,
    EMAIL            VARCHAR(255) not null,
    ADDRESS          VARCHAR(255) not null,
    STATUS           VARCHAR(255) not null,
    PASSWORD         VARCHAR(60)
);

create table PATH
(
    ID   INTEGER identity
        constraint PATH_PK
            primary key,
    NAME VARCHAR(255) not null
);

create table PORT
(
    ID     INTEGER identity
        constraint PORT_PK
            primary key,
    NAME   VARCHAR(255) not null,
    GPS    VARCHAR(255) not null,
    STATUS VARCHAR(255) not null
);

comment on column PORT.GPS is 'latitude and longitude';

create table PATH_PORT
(
    ID       INTEGER identity
        constraint PATH_PORT_PK
            primary key,
    PATH_FK  INTEGER not null
        constraint PATH_PORT_PATH_ID_FK
            references PATH
            on update cascade on delete cascade,
    PORT_FK  INTEGER not null
        constraint PATH_PORT_PORT_ID_FK
            references PORT
            on update cascade,
    PREVIOUS INTEGER
        constraint PATH_PORT_PATH_PORT_ID_FK
            references PATH_PORT
            on update cascade on delete set null,
    NEXT     INTEGER
        constraint PATH_PORT_PATH_PORT_ID_FK_2
            references PATH_PORT
            on update cascade on delete set null
);

create table SHIP
(
    ID           INTEGER identity
        constraint SHIP_PK
            primary key,
    PORT_FK      INTEGER
        constraint SHIP_PORT_ID_FK
            references PORT,
    PATH_FK      INTEGER
        constraint SHIP_PATH_ID_FK
            references PATH
            on update set null on delete set null,
    NAME         VARCHAR(255) not null,
    CURRENT_NODE INTEGER
        constraint SHIP_PATH_PORT_ID_FK
            references PATH_PORT
            on update set null on delete set null
);

create table CONTAINER
(
    ID         INTEGER identity
        constraint CONTAINER_PK
            primary key,
    CLIENT_FK  INTEGER
        constraint CONTAINER_CLIENT_ID_FK
            references CLIENT
            on update cascade,
    NAME       VARCHAR(255)          not null,
    ON_JOURNEY BOOLEAN default FALSE not null,
    PORT_FK    INTEGER
        constraint CONTAINER_PORT_ID_FK
            references PORT
            on update set null on delete set null,
    SHIP_FK    INTEGER
        constraint CONTAINER_SHIP_ID_FK
            references SHIP
            on update set null on delete set null
);

create table JOURNEY
(
    ID                INTEGER identity
        constraint JOURNEY_PK
            primary key,
    CONTAINER_FK      INTEGER
        constraint JOURNEY_CONTAINER_ID_FK
            references CONTAINER
            on update cascade on delete cascade,
    PATH_FK           INTEGER
        constraint JOURNEY_PATH_ID_FK
            references PATH
            on update cascade on delete cascade,
    CLIENT_FK         INTEGER      not null
        constraint JOURNEY_CLIENT_ID_FK
            references CLIENT
            on update cascade on delete cascade,
    ORIGIN            INTEGER      not null
        constraint JOURNEY_PORT_ID_FK_ORIGIN
            references PORT,
    DESTINATION       INTEGER      not null
        constraint JOURNEY_PORT_ID_FK_DESTINATION
            references PORT,
    CURRENT_LOCATION  INTEGER      not null
        constraint JOURNEY_PORT_ID_FK_CURRENT
            references PORT,
    NEXT_LOCATION     INTEGER
        constraint JOURNEY_PORT_FK_NEXT
            references PORT
            on update set null on delete set null,
    SAIL_STATUS       VARCHAR(255) not null,
    CONTAINER_CONTENT VARCHAR(255) not null
);

create table CONTAINER_STATUS
(
    ID           INTEGER identity
        constraint CONTAINER_STATUS_PK
            primary key,
    JOURNEY_FK   INTEGER                             not null
        constraint CONTAINER_STATUS_JOURNEY_ID_FK
            references JOURNEY
            on update cascade on delete cascade,
    STATUS_NAME  VARCHAR(255)                        not null,
    STATUS_VALUE VARCHAR(255)                        not null,
    DATE         TIMESTAMP default LOCALTIMESTAMP(0) not null
);

create table EVENT
(
    ID            INTEGER identity
        constraint EVENT_PK
            primary key,
    JOURNEY_FK    INTEGER                             not null
        constraint EVENT_JOURNEY_ID_FK
            references JOURNEY
            on update cascade on delete cascade,
    EVENT_NAME    VARCHAR(255)                        not null,
    EVENT_MESSAGE VARCHAR(512)                        not null,
    DATE          TIMESTAMP default LOCALTIMESTAMP(0) not null
);

create table LOCATION
(
    ID         INTEGER identity
        constraint LOCATION_PK
            primary key,
    JOURNEY_FK INTEGER                             not null
        constraint LOCATION_JOURNEY_ID_FK
            references JOURNEY
            on update cascade on delete cascade,
    PORT_FK    INTEGER                             not null
        constraint LOCATION_PORT_ID_FK
            references PORT
            on update cascade,
    DATE       TIMESTAMP default LOCALTIMESTAMP(0) not null
);

