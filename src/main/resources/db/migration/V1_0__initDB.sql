create sequence user_sequence start 1 increment 1;

create table monitor (
                         id int8 not null ,
                         created_at timestamp,
                         created_by varchar(255),
                         updated_at timestamp,
                         CHECK ( created_at<=updated_at),
                         updated_by varchar(255),
                         length int8,
                         CHECK ( length>10),
                         height int8,
                         CHECK ( height>10),
                         width int8,
                         CHECK (height>10),
                         vesa varchar(255),
                         display_size varchar(255) not null ,
                         w_place_id int8 not null,
                         primary key (id)

);

create table working_place (
                     id int8 not null,
                     created_at timestamp,
                     created_by varchar(255),
                     updated_at timestamp,
                     CHECK ( created_at<=updated_at),
                     updated_by varchar(255),
                     name varchar(255) not null ,
                     city varchar(255),
                     primary key (id)
);

create table pc (
                         id int8 not null,
                         created_at timestamp,
                         created_by varchar(255),
                         updated_at timestamp,
                         CHECK ( created_at<=updated_at),
                         updated_by varchar(255),
                        length int8,
                         CHECK ( length>10),
                         height int8,
                         CHECK ( height>10),
                         width int8,
                         CHECK (height>10),
                         hddSize int8 not null ,
                         cpuCount int8 not null ,
                         w_place_id int8 not null,
                         primary key (id)
);



alter table if exists monitor
    add constraint monitor_wplace_fk
        foreign key (id) references working_place
            ON DELETE  CASCADE
            ON UPDATE CASCADE ;

alter table if exists pc
    add constraint pc_wplace_fk
        foreign key (id) references working_place
            ON DELETE  CASCADE
    ON UPDATE CASCADE ;

