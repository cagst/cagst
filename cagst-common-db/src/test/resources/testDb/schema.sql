DROP TABLE cgt_sequence IF EXISTS;

CREATE TABLE cgt_sequence (
	sequence_name		VARCHAR(25) NOT NULL PRIMARY KEY,
	next_seq				BIGINT DEFAULT 10000 NOT NULL
);
