-- DROP triggers
DROP TRIGGER IF EXISTS cgt_codevalue_history;
DROP TRIGGER IF EXISTS cgt_codeset_history;
DROP TRIGGER IF EXISTS cgt_user_history;
DROP TRIGGER IF EXISTS cgt_person_history;
DROP TRIGGER IF EXISTS cgt_client_history;

-- Drop history tables
DROP TABLE IF EXISTS cgt_codevalue_hist;
DROP TABLE IF EXISTS cgt_codeset_hist;
DROP TABLE IF EXISTS cgt_user_hist;
DROP TABLE IF EXISTS cgt_person_hist;
DROP TABLE IF EXISTS cgt_client_hist;

DROP TABLE IF EXISTS cgt_audit_log;
DROP TABLE IF EXISTS cgt_zipcode;
DROP TABLE IF EXISTS cgt_codevalue;
DROP TABLE IF EXISTS cgt_codeset;
DROP TABLE IF EXISTS cgt_user;
DROP TABLE IF EXISTS cgt_person;
DROP TABLE IF EXISTS cgt_client;
