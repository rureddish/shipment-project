create table "client"
(
    "id"               INTEGER identity
        constraint CLIENT_PK
            primary key,
    "client_name"      VARCHAR(255) not null,
    "reference_person" VARCHAR(255) not null,
    "email"            VARCHAR(255) not null,
    "address"          VARCHAR(255) not null,
    "status"           VARCHAR(255) not null
);

create table "container"
(
    "id"         INTEGER identity
        constraint """container""_pk"
            primary key,
    "client_fk"  INTEGER
        constraint """container""_client_id_fk"
            references "client"
            on update cascade,
    "name"       VARCHAR(255)          not null,
    "on_journey" BOOLEAN default FALSE not null
);

create table "path"
(
    "id"   INTEGER identity
        constraint """path""_pk"
            primary key,
    "name" VARCHAR(255) not null
);

create table "port"
(
    "id"     INTEGER      not null
        constraint """port""_pk"
            primary key,
    "name"   VARCHAR(255) not null,
    "gps"    VARCHAR(255) not null,
    "status" VARCHAR(255) not null
);

comment on column "port"."gps" is 'latitude and longitude';

create table "path_port"
(
    "id"       INTEGER identity
        constraint """path_port""_pk"
            primary key,
    "path_fk"  INTEGER not null
        constraint PATH_PORT_PATH_ID_FK
            references "path"
            on update cascade on delete cascade,
    "port_fk"  INTEGER not null
        constraint PATH_PORT_PORT_ID_FK
            references "port"
            on update cascade,
    "previous" INTEGER
        constraint PATH_PORT_PATH_PORT_ID_FK
            references "path_port"
            on update cascade on delete set null,
    "next"     INTEGER
        constraint PATH_PORT_PATH_PORT_ID_FK_2
            references "path_port"
            on update cascade on delete set null
);

create table "journey"
(
    "id"                INTEGER identity
        constraint """journey""_pk"
            primary key,
    "container_fk"      INTEGER      not null
        constraint JOURNEY_CONTAINER_ID_FK
            references "container"
            on update cascade on delete cascade,
    "path_fk"           INTEGER      not null
        constraint JOURNEY_PATH_ID_FK
            references "path"
            on update cascade on delete cascade,
    "client_fk"         INTEGER      not null
        constraint JOURNEY_CLIENT_ID_FK
            references "client"
            on update cascade on delete cascade,
    "origin"            INTEGER      not null
        constraint JOURNEY_PATH_PORT_ID_FK
            references "path_port"
            on update cascade on delete cascade,
    "destination"       INTEGER      not null
        constraint JOURNEY_PATH_PORT_ID_FK_2
            references "path_port"
            on update cascade on delete cascade,
    "current_location"  INTEGER      not null
        constraint JOURNEY_PATH_PORT_ID_FK_4
            references "path_port"
            on update cascade on delete cascade,
    "next_location"     INTEGER
        constraint JOURNEY_PATH_PORT_ID_FK_3
            references "path_port"
            on update cascade on delete set null,
    "sail_status"       VARCHAR(255) not null,
    "container_content" VARCHAR(255) not null
);

create table "container_status"
(
    "id"           INTEGER identity
        constraint """container_status""_pk"
            primary key,
    "journey_fk"   INTEGER                             not null
        constraint """container_status""_journey_id_fk"
            references "journey"
            on update cascade on delete cascade,
    "status_name"  VARCHAR(255)                        not null,
    "status_value" VARCHAR(255)                        not null,
    "date"         TIMESTAMP default LOCALTIMESTAMP(0) not null
);

create table "event"
(
    "id"            INTEGER identity
        constraint """event""_pk"
            primary key,
    "journey_fk"    INTEGER                             not null
        constraint """event""_journey_id_fk"
            references "journey"
            on update cascade on delete cascade,
    "event_name"    VARCHAR(255)                        not null,
    "event_message" VARCHAR(512)                        not null,
    "date"          TIMESTAMP default LOCALTIMESTAMP(0) not null
);

create table "location"
(
    "id"         INTEGER identity
        constraint """location""_pk"
            primary key,
    "journey_fk" INTEGER                             not null
        constraint """location""_journey_id_fk"
            references "journey"
            on update cascade on delete cascade,
    "port_fk"    INTEGER                             not null
        constraint """location""_port_id_fk"
            references "port"
            on update cascade,
    "date"       TIMESTAMP default LOCALTIMESTAMP(0) not null
);

