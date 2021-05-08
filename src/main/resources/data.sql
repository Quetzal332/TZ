CREATE  TABLE IF NOT EXISTS clients (
        id              UUID PRIMARY KEY NOT NULL ,
        first_name      varchar(56),
        second_name     varchar(56),
        last_name       varchar(56),
        phonenum        numeric NOT NULL  UNIQUE ,
        email           varchar(70) NOT NULL UNIQUE ,
        passnum         varchar(70) NOT NULL UNIQUE ,
        bank_id         UUID NOT NULL FOREIGN KEY REFERENCES banks(id)
                                    );

CREATE TABLE IF NOT EXISTS credits(
        id              UUID PRIMARY KEY NOT NULL ,
        name            varchar(70) NOT NULL,
        credit_limit    bigint NOT NULL ,
        interest_rate   decimal NOT NULL ,
        bank_id         UUID NOT NULL FOREIGN KEY REFERENCES banks(id)
                                  );

CREATE TABLE IF NOT EXISTS banks(
        id              UUID PRIMARY KEY NOT NULL ,
        name            varchar(30) NOT NULL
                                );

CREATE  TABLE  IF NOT EXISTS credit_offers(
        id              UUID PRIMARY KEY NOT NULL ,
        client_id       UUID NOT NULL FOREIGN KEY REFERENCES clients(id),
        credit_id       UUID NOT NULL FOREIGN KEY REFERENCES credits(id),
        credit_sum      BIGINT NOT NULL ,
        month           NUMERIC ,
        schedule_id     UUID NOT NULL FOREIGN KEY REFERENCES payment_schedule(id)
);

CREATE TABLE IF NOT EXISTS payment_schedule(
        id              UUID PRIMARY KEY  NOT NULL ,
        sum_payment     decimal ,
        sum_body        decimal ,
        sum_percent     decimal
);

INSERT INTO banks (id,name) VALUES (UUID('161999c8-a050-4023-abf1-9c3173478667'),'bank1');
INSERT INTO banks (id,name) VALUES (UUID('161999c8-a020-4023-abf1-9c3173478667'),'bank2');
INSERT INTO credits (id,name, credit_limit, interest_rate,bank_id) VALUES (UUID('6cf9b9e9-02d8-4cfe-874d-bf1960ceefd9'),'credit1',700000,16.9,UUID('161999c8-a050-4023-abf1-9c3173478667'));
INSERT INTO credits (id,name, credit_limit, interest_rate,bank_id) VALUES (UUID('eaf6396b-7bc8-4f24-8555-41bf51839f23'),'cred2',1000000,10,UUID('161999c8-a050-4023-abf1-9c3173478667'));
INSERT INTO credits (id,name, credit_limit, interest_rate,bank_id) VALUES (UUID('2e9f70cb-a53a-4531-95c1-d8505144adf0'),'cred3',1000000,10,UUID('161999c8-a020-4023-abf1-9c3173478667'));
INSERT INTO clients (id,first_name,second_name,last_name, phonenum, email, passnum,bank_id) VALUES (UUID('00876184-3bb7-437b-b19e-7a7202120c41'),'Ruslan','valdimirovich','aipov',87987964941,'asdkl@gmail.com','32424633124',UUID('161999c8-a050-4023-abf1-9c3173478667'));
INSERT INTO clients (id,first_name,second_name,last_name, phonenum, email, passnum,bank_id) VALUES (UUID('4dea3d98-41da-4829-809b-f3c2f8033e3e'),'Igor','Leonidovich','Egorov',89998723141,'asd34kl@gmail.com','543677343124',UUID('161999c8-a050-4023-abf1-9c3173478667'));
INSERT INTO clients (id,first_name,second_name,last_name, phonenum, email, passnum,bank_id) VALUES (UUID('2089f505-955a-41a8-ba2e-316b86fd1c12'),'Lyudmila','Anatolyevna','Melnikova',43562346345,'asdkl32@gmail.com','32467343124',UUID('161999c8-a020-4023-abf1-9c3173478667'));
INSERT INTO  payment_schedule (id, sum_payment, sum_body, sum_percent) VALUES (UUID('0266ef4e-6040-4770-b811-aa4a464f296e'),2800,745,413);
INSERT INTO credit_offers (id, client_id, credit_id, credit_sum,month, schedule_id) VALUES (UUID('9b0624e0-0ac4-4185-948d-27f412150949'),UUID('00876184-3bb7-437b-b19e-7a7202120c41'),UUID('6cf9b9e9-02d8-4cfe-874d-bf1960ceefd9'),500000,12,UUID('0266ef4e-6040-4770-b811-aa4a464f296e'));
