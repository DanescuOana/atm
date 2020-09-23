CREATE DATABASE tech_atm;

CREATE TABLE USER(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE CARD (
    card_number BIGINT PRIMARY KEY AUTO_INCREMENT,
    pin INT NOT NULL
);

ALTER TABLE CARD AUTO_INCREMENT=100001;

CREATE TABLE CURRENCY(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    unit VARCHAR(3) NOT NULL
);

CREATE TABLE ACCOUNT_TYPE(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_type VARCHAR(100) NOT NULL,
    possibility_extract_from_atm INT NOT NULL DEFAULT 1,
    currency_id BIGINT NOT NULL,
    CHECK (possibility_extract_from_atm = 0 OR possibility_extract_from_atm = 1),
    FOREIGN KEY (currency_id) REFERENCES CURRENCY(id)
);

CREATE TABLE ACCOUNT(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    iban varchar(24) NOT NULL,
    balance_account DECIMAL DEFAULT 0,
    account_type_id BIGINT NOT NULL,
    card_number BIGINT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (account_type_id) REFERENCES ACCOUNT_TYPE(id),
    FOREIGN KEY (card_number) REFERENCES CARD(card_number),
    FOREIGN KEY (user_id) REFERENCES USER(id)
);
<!---------------INSERT INTO USER-------->
INSERT INTO USER(username, name)
VALUES('mlaurentiu23', 'Mitrache Laurentiu');

INSERT INTO USER(username, name)
VALUES('vioana03', 'Vasile Ioana');

INSERT INTO USER(username, name)
VALUES('rfilofteia01', 'Rogojina Filofteia');

<!---------------INSERT INTO CARD-------->
INSERT INTO CARD(pin)
VALUES(1234);

INSERT INTO CARD(pin)
VALUES(1234);

INSERT INTO CARD(pin)
VALUES(1234);

INSERT INTO CARD(pin)
VALUES(1234);


<!---------------INSERT INTO CURRENCY-------->
INSERT INTO CURRENCY(name, unit)
VALUES('Leu romanesc', 'RON');
INSERT INTO CURRENCY(name, unit)
VALUES('Euro', 'EUR');
INSERT INTO CURRENCY(name, unit)
VALUES('Lira sterlina', 'GBP');
INSERT INTO CURRENCY(name, unit)
VALUES('Dolar America', 'USD');


<!---------------INSERT INTO ACCOUNT_TYPE-------->

INSERT INTO ACCOUNT_TYPE(account_type, possibility_extract_from_atm, currency_id)
VALUES('Cont Economii', 0, 2);
INSERT INTO ACCOUNT_TYPE(account_type, possibility_extract_from_atm, currency_id)
VALUES('Cont Curent', 1, 1);
INSERT INTO ACCOUNT_TYPE(account_type, possibility_extract_from_atm, currency_id)
VALUES('Cont Cumparaturi', 0, 1);

<!----------------INSERT INTO ACCOUNT----------->
INSERT INTO ACCOUNT(iban, balance_account, account_type_id, card_number, user_id)
values('RO49AAAA1B31007593840000', 345.45, 4, 100001, 1);

INSERT INTO ACCOUNT(iban, balance_account, account_type_id, card_number, user_id)
values('RO49AAAA1B31007593840001', 1.45, 5, 100002, 2);

INSERT INTO ACCOUNT(iban, balance_account, account_type_id, card_number, user_id)
values('RO49AAAA1B31007593840002', 34.45, 6, 100003, 3);

INSERT INTO ACCOUNT(iban, balance_account, account_type_id, card_number, user_id)
values('RO49AAAA1B31007593840003', 22344, 5, 100004, 1);
