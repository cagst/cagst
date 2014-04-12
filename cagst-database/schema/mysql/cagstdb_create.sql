CREATE TABLE cgt_client (
	cgt_client_id					BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	client_name						VARCHAR(50) NOT NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_client_pk PRIMARY KEY (cgt_client_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_client_hist (
	cgt_client_hist_id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_client_id					BIGINT UNSIGNED NOT NULL,
	client_name						VARCHAR(50) NOT NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_client_hist_pk PRIMARY KEY (cgt_client_hist_id),
	CONSTRAINT cgt_client_hist_fk1 FOREIGN KEY (cgt_client_id) REFERENCES cgt_client (cgt_client_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_person (
	cgt_person_id					BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_client_id					BIGINT UNSIGNED NULL,
	name_last							VARCHAR(50) NOT NULL,
	name_last_key					VARCHAR(50) NOT NULL,
	name_first						VARCHAR(50) NOT NULL,
	name_first_key				VARCHAR(50) NOT NULL,
	name_middle						VARCHAR(50) NULL,
	dob_dt_tm							TIMESTAMP NULL,
	gender_cd							BIGINT UNSIGNED NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_person_pk PRIMARY KEY (cgt_person_id),
	CONSTRAINT cgt_person_fk1 FOREIGN KEY (cgt_client_id) REFERENCES cgt_client (cgt_client_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_person_hist (
	cgt_person_hist_id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_person_id					BIGINT UNSIGNED NOT NULL,
	cgt_client_id					BIGINT UNSIGNED NULL,
	name_last							VARCHAR(50) NOT NULL,
	name_last_key					VARCHAR(50) NOT NULL,
	name_first						VARCHAR(50) NOT NULL,
	name_first_key				VARCHAR(50) NOT NULL,
	name_middle						VARCHAR(50) NULL,
	dob_dt_tm							TIMESTAMP NULL,
	gender_cd							BIGINT UNSIGNED NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_person_hist_pk PRIMARY KEY (cgt_person_hist_id),
	CONSTRAINT cgt_person_hist_fk1 FOREIGN KEY (cgt_person_id) REFERENCES cgt_person (cgt_person_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_user (
	cgt_user_id						BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_person_id					BIGINT UNSIGNED NOT NULL,
	username							VARCHAR(50) NOT NULL,
	password							VARCHAR(50) NOT NULL,
	expire_dt_tm					DATETIME NULL,
	last_signin_dt_tm			DATETIME NULL,
	signin_attempts				INT UNSIGNED NOT NULL DEFAULT 0,
	change_password_ind		BOOLEAN NOT NULL DEFAULT 1,
	account_locked_dt_tm	DATETIME NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_user_pk PRIMARY KEY (cgt_user_id),
	CONSTRAINT cgt_user_fk1 FOREIGN KEY (cgt_person_id) REFERENCES cgt_person (cgt_person_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_user_hist (
	cgt_user_hist_id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_user_id						BIGINT UNSIGNED NOT NULL,
	cgt_person_id					BIGINT UNSIGNED NOT NULL,
	username							VARCHAR(50) NOT NULL,
	password							VARCHAR(50) NOT NULL,
	expire_dt_tm					DATETIME NULL,
	last_signin_dt_tm			DATETIME NULL,
	signin_attempts				INT UNSIGNED NOT NULL DEFAULT 0,
	change_password_ind		BOOLEAN NOT NULL DEFAULT 1,
	account_locked_dt_tm	DATETIME NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_user_hist_pk PRIMARY KEY (cgt_user_hist_id),
	CONSTRAINT cgt_user_hist_fk1 FOREIGN KEY (cgt_user_id) REFERENCES cgt_user (cgt_user_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_codeset (
	cgt_codeset_id				BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	codeset_display				VARCHAR(50) NOT NULL,
	codeset_meaning				VARCHAR(25) NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_codeset_pk PRIMARY KEY (cgt_codeset_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_codeset_hist (
	cgt_codeset_hist_id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_codeset_id				BIGINT UNSIGNED NOT NULL,
	codeset_display				VARCHAR(50) NOT NULL,
	codeset_meaning				VARCHAR(25) NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_codeset_hist_pk PRIMARY KEY (cgt_codeset_hist_id),
	CONSTRAINT cgt_codeset_hist_fk1 FOREIGN KEY (cgt_codeset_id) REFERENCES cgt_codeset (cgt_codeset_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_codevalue (
	cgt_codevalue_id			BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_codeset_id				BIGINT UNSIGNED NOT NULL,
	cgt_client_id					BIGINT UNSIGNED NULL,
	codevalue_display			VARCHAR(50) NOT NULL,
	codevalue_meaning			VARCHAR(25) NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_codevalue_pk PRIMARY KEY (cgt_codevalue_id),
	CONSTRAINT cgt_codevalue_fk1 FOREIGN KEY (cgt_codeset_id) REFERENCES cgt_codeset (cgt_codeset_id),
	CONSTRAINT cgt_codevalue_fk2 FOREIGN KEY (cgt_client_id) REFERENCES cgt_client (cgt_client_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_codevalue_hist (
	cgt_codevalue_hist_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	cgt_codevalue_id			BIGINT UNSIGNED NOT NULL,
	cgt_codeset_id				BIGINT UNSIGNED NOT NULL,
	cgt_client_id					BIGINT UNSIGNED NULL,
	codevalue_display			VARCHAR(50) NOT NULL,
	codevalue_meaning			VARCHAR(25) NULL,
	active_ind						BOOLEAN NOT NULL DEFAULT 1,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	updt_dt_tm						TIMESTAMP NOT NULL,
	updt_id								BIGINT UNSIGNED NOT NULL,
	updt_cnt							INT UNSIGNED NOT NULL DEFAULT 0,
	CONSTRAINT cgt_codevalue_hist_pk PRIMARY KEY (cgt_codevalue_hist_id),
	CONSTRAINT cgt_codevalue_hist_fk1 FOREIGN KEY (cgt_codevalue_id) REFERENCES cgt_codevalue (cgt_codevalue_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_audit_log (
	cgt_audit_log_id			BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	audit_event						VARCHAR(50) NOT NULL,
	audit_action					VARCHAR(50) NOT NULL,
	audit_message					VARCHAR(250) NULL,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	CONSTRAINT cgt_audit_log_pk PRIMARY KEY (cgt_audit_log_id)
) ENGINE = InnoDB;

CREATE TABLE cgt_zipcode (
	zipcode								VARCHAR(10) NOT NULL,
	state_cd							BIGINT UNSIGNED NOT NULL,
	county_cd							BIGINT UNSIGNED NOT NULL,
	city									VARCHAR(50) NOT NULL,
	create_dt_tm					TIMESTAMP NOT NULL,
	create_id							BIGINT UNSIGNED NOT NULL,
	CONSTRAINT cgt_zpcode_pk PRIMARY KEY (zipcode),
	CONSTRAINT cgt_zipcode_fk1 FOREIGN KEY (state_cd) REFERENCES cgt_codevalue (cgt_codevalue_id),
	CONSTRAINT cgt_zipcode_fk2 FOREIGN KEY (county_cd) REFERENCES cgt_codevalue (cgt_codevalue_id)
) ENGINE=InnoDB;

delimiter |

CREATE TRIGGER cgt_client_history AFTER UPDATE ON cgt_client
  FOR EACH ROW BEGIN
    INSERT INTO cgt_client_hist (cgt_client_id, client_name, active_ind, create_id, create_dt_tm, updt_id, updt_dt_tm, updt_cnt)
    VALUES (old.cgt_client_id, old.client_name, old.active_ind, old.create_id, old.create_dt_tm, old.updt_id, old.updt_dt_tm, old.updt_cnt);
  END;
|

CREATE TRIGGER cgt_person_history AFTER UPDATE ON cgt_person
  FOR EACH ROW BEGIN
    INSERT INTO cgt_person_hist (cgt_person_id, cgt_client_id, name_last, name_last_key, name_middle, name_first, name_first_key, dob_dt_tm, gender_cd, active_ind, create_id, create_dt_tm, updt_id, updt_dt_tm, updt_cnt)
    VALUES (old.cgt_person_id, old.cgt_client_id, old.name_last, old.name_last_key, old.name_middle, old.name_first, old.name_first_key, old.dob_dt_tm, old.gender_cd, old.active_ind, old.create_id, old.create_dt_tm, old.updt_id, old.updt_dt_tm, old.updt_cnt);
  END;
|

CREATE TRIGGER cgt_user_history AFTER UPDATE ON cgt_user
  FOR EACH ROW BEGIN
    IF old.username != new.username OR
       old.password != new.password OR
       old.expire_dt_tm != new.expire_dt_tm OR
       old.change_password_ind != new.change_password_ind OR
       old.account_locked_dt_tm != new.account_locked_dt_tm OR
       old.active_ind != new.active_ind THEN
      INSERT INTO cgt_user_hist (cgt_user_id, cgt_person_id, username, password, expire_dt_tm, last_signin_dt_tm, signin_attempts, change_password_ind, account_locked_dt_tm, active_ind, create_id, create_dt_tm, updt_id, updt_dt_tm, updt_cnt)
      VALUES (old.cgt_user_id, old.cgt_person_id, old.username, old.password, old.expire_dt_tm, old.last_signin_dt_tm, old.signin_attempts, old.change_password_ind, old.account_locked_dt_tm, old.active_ind, old.create_id, old.create_dt_tm, old.updt_id, old.updt_dt_tm, old.updt_cnt);
    END IF;
  END;
|

CREATE TRIGGER cgt_codeset_history AFTER UPDATE ON cgt_codeset
  FOR EACH ROW BEGIN
    INSERT INTO cgt_codeset_hist (cgt_codeset_id, codeset_display, codeset_meaning, active_ind, create_id, create_dt_tm, updt_id, updt_dt_tm, updt_cnt)
    VALUES (old.cgt_codeset_id, old.codeset_display, old.codeset_meaning, old.active_ind, old.updt_id, old.create_id, old.create_dt_tm, old.updt_dt_tm, old.updt_cnt);
  END;
|

CREATE TRIGGER cgt_codevalue_history AFTER UPDATE ON cgt_codevalue
  FOR EACH ROW BEGIN
    INSERT INTO cgt_codevalue_hist (cgt_codevalue_id, cgt_codeset_id, cgt_client_id, codevalue_display, codevalue_meaning, active_ind, create_id, create_dt_tm, updt_id, updt_dt_tm, updt_cnt)
    VALUES (old.cgt_codevalue_id, old.cgt_codeset_id, old.cgt_client_id, old.codevalue_display, old.codevalue_meaning, old.active_ind, old.create_id, old.create_dt_tm, old.updt_id, old.updt_dt_tm, old.updt_cnt);
  END;
|
