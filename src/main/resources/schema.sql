DROP TABLE IF EXISTS credit_card_details;
CREATE TABLE credit_card_details(
card_number VARCHAR(20),
card_holder_name VARCHAR(250) NOT NULL,
balance DOUBLE,
PRIMARY KEY(card_number)
);

Drop TABLE IF EXISTS user;
CREATE TABLE user(
user_name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
user_role VARCHAR(20) NOT NULL,
access_token VARCHAR(300),
PRIMARY KEY(user_name)
);