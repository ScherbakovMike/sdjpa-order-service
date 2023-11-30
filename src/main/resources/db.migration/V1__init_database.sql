drop table if exists debit_acount cascade;
drop table if exists credit_account;

create table account
(
    id           bigint not null auto_increment primary key,
    balance      numeric(19, 2),
    interest_rate numeric(19, 2),
    owner        varchar(255)
) engine = InnoDB;

create table debit_account
(
    id           bigint not null auto_increment primary key,
    balance      numeric(19, 2),
    interest_rate numeric(19, 2),
    owner        varchar(255),
    over_draft_fee numeric(19, 2)
) engine = InnoDB;

create table credit_account
(
    id           bigint not null auto_increment primary key,
    balance      numeric(19, 2),
    interest_rate numeric(19, 2),
    owner        varchar(255),
    credit_limit  numeric(19, 2)
) engine = InnoDB;
