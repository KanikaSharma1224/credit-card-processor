DROP TABLE IF EXISTS credit_card_details;
CREATE TABLE credit_card_details(
card_number VARCHAR(20),
card_holder_name VARCHAR(250) NOT NULL,
balance DOUBLE,
PRIMARY KEY(card_number)
);