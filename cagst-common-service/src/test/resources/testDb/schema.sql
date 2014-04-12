DROP TABLE cgt_codevalue IF EXISTS;
DROP TABLE cgt_codeset IF EXISTS;
DROP TABLE cgt_user IF EXISTS;
DROP TABLE cgt_person IF EXISTS;
DROP TABLE cgt_client IF EXISTS;

CREATE TABLE cgt_client (
	cgt_client_id					BIGINT NOT NULL,
	client_name						VARCHAR(100) NOT NULL,
	active_ind						TINYINT DEFAULT 1 NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT NOT NULL,
	updt_cnt							INT DEFAULT 0 NOT NULL,
	beg_eff_dt_tm					TIMESTAMP NOT NULL,
	end_eff_dt_tm					TIMESTAMP NULL,
	CONSTRAINT cgt_client_pk PRIMARY KEY (cgt_client_id)
);

CREATE TABLE cgt_person (
	cgt_person_id					BIGINT NOT NULL,
	cgt_client_id					BIGINT NULL,
	name_last							VARCHAR(100) NOT NULL,
	name_last_key					VARCHAR(100) NOT NULL,
	name_first						VARCHAR(100) NOT NULL,
	name_first_key				VARCHAR(100) NOT NULL,
	name_middle						VARCHAR(100) NULL,
	dob_dt_tm							TIMESTAMP NULL,
	active_ind						TINYINT DEFAULT 1 NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT NOT NULL,
	updt_cnt							INT DEFAULT 0 NOT NULL,
	beg_eff_dt_tm					TIMESTAMP NOT NULL,
	end_eff_dt_tm					TIMESTAMP NULL,
	CONSTRAINT cgt_person_pk PRIMARY KEY (cgt_person_id),
	CONSTRAINT cgt_person_fk1 FOREIGN KEY (cgt_client_id) REFERENCES cgt_client (cgt_client_id)
);

CREATE TABLE cgt_user (
	cgt_user_id						BIGINT NOT NULL,
	cgt_person_id					BIGINT NOT NULL,
	username							VARCHAR(50) NOT NULL,
	password							VARCHAR(50) NOT NULL,
	expire_dt_tm					DATETIME NULL,
	last_signin_dt_tm			DATETIME NULL,
	signin_attempts				INT DEFAULT 0 NOT NULL,
	change_password_ind		BOOLEAN DEFAULT 1 NOT NULL,
	account_locked_dt_tm	DATETIME NULL,
	active_ind						BOOLEAN DEFAULT 1 NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT NOT NULL,
	updt_cnt							INT DEFAULT 0 NOT NULL,
	beg_eff_dt_tm					TIMESTAMP NOT NULL,
	end_eff_dt_tm					TIMESTAMP NULL,
	CONSTRAINT cgt_user_pk PRIMARY KEY (cgt_user_id),
	CONSTRAINT cgt_user_fk1 FOREIGN KEY (cgt_person_id) REFERENCES cgt_person (cgt_person_id)
);

CREATE TABLE cgt_codeset (
	cgt_codeset_id				BIGINT NOT NULL,
	codeset_display				VARCHAR(50) NOT NULL,
	codeset_meaning				VARCHAR(25) NULL,
	active_ind						TINYINT DEFAULT 1 NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT NOT NULL,
	updt_cnt							INT DEFAULT 0 NOT NULL,
	beg_eff_dt_tm					TIMESTAMP NOT NULL,
	end_eff_dt_tm					TIMESTAMP NULL,
	CONSTRAINT cgt_codeset_pk PRIMARY KEY (cgt_codeset_id)
);

CREATE TABLE cgt_codevalue (
	cgt_codevalue_id			BIGINT NOT NULL,
	cgt_codeset_id				BIGINT NOT NULL,
	cgt_client_id					BIGINT NULL,
	codevalue_display			VARCHAR(50) NOT NULL,
	codevalue_meaning			VARCHAR(25) NULL,
	active_ind						TINYINT DEFAULT 1 NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT NOT NULL,
	updt_cnt							INT DEFAULT 0 NOT NULL,
	beg_eff_dt_tm					TIMESTAMP NOT NULL,
	end_eff_dt_tm					TIMESTAMP NULL,
	CONSTRAINT cgt_codevalue_pk PRIMARY KEY (cgt_codevalue_id),
	CONSTRAINT cgt_codevalue_fk1 FOREIGN KEY (cgt_codeset_id) REFERENCES cgt_codeset (cgt_codeset_id),
	CONSTRAINT cgt_codevalue_fk2 FOREIGN KEY (cgt_client_id) REFERENCES cgt_client (cgt_client_id)
);
