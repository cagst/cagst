INSERT INTO cgt_client (cgt_client_id, client_name, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 'Aussie Pet Mobile - Olathe', 1, NOW(), NOW());
INSERT INTO cgt_client (cgt_client_id, client_name, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 'Cutting Edge Mobile Grooming', 1, NOW(), NOW());

INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 'Address Type', 'ADDRESS_TYPE', 1, NOW(), NOW());
INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 'Phone Type', 'PHONE_TYPE', 1, NOW(), NOW());
INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (3, 'Email Type', 'EMAIL_TYPE', 1, NOW(), NOW());
INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, active_ind, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (4, 'Document Type', 'DOCUMENT_TYPE', 0, 1, NOW(), NOW());

INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 1, 'Home', 'HOME', 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 2, 'Work', 'WORK', 1, NOW(), NOW());

INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 3, 'Home', 'HOME', 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 4, 'Work', 'WORK', 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, cgt_client_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 5, 1, 'Mobile', 'MOBILE', 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, active_ind, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 6, 'Fax', 'FAX', 1, 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, cgt_client_id, codevalue_display, codevalue_meaning, active_ind, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 7, 1, 'Pager', 'PAGER', 1, 1, NOW(), NOW());

INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (3, 8, 'Home', 'HOME', 1, NOW(), NOW());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (3, 9, 'Work', 'WORK', 1, NOW(), NOW());

INSERT INTO cgt_person (cgt_person_id, name_last, name_last_key, name_first, name_first_key, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 'Gaskill', 'GASKILL', 'Craig', 'CRAIG', 1, NOW(), NOW());
INSERT INTO cgt_person (cgt_person_id, name_last, name_last_key, name_first, name_first_key, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 'Temp', 'TEMP', 'User', 'USER', 1, NOW(), NOW());
INSERT INTO cgt_person (cgt_person_id, name_last, name_last_key, name_first, name_first_key, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (3, 'Expired', 'EXPIRED', 'User', 'USER', 1, NOW(), NOW());
INSERT INTO cgt_person (cgt_person_id, name_last, name_last_key, name_first, name_first_key, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (4, 'Locked', 'LOCKED', 'User', 'USER', 1, NOW(), NOW());

INSERT INTO cgt_user (cgt_user_id, cgt_person_id, username, password, change_password_ind, last_signin_dt_tm, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (1, 1, 'cgaskill', 'cgaskill', 0, '2012-12-25 15:00:00.0000', 1, NOW(), NOW());
INSERT INTO cgt_user (cgt_user_id, cgt_person_id, username, password, change_password_ind, expire_dt_tm, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (2, 2, 'temp', 'temp', 1, '2099-12-25 15:00:00.0000', 1, NOW(), NOW());
INSERT INTO cgt_user (cgt_user_id, cgt_person_id, username, password, change_password_ind, expire_dt_tm, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (3, 3, 'expire', 'expire', 1, '2011-12-25 15:00:00.0000', 1, NOW(), NOW());
INSERT INTO cgt_user (cgt_user_id, cgt_person_id, username, password, change_password_ind, account_locked_dt_tm, signin_attempts, updt_id, updt_dt_tm, beg_eff_dt_tm) VALUES (4, 4, 'locked', 'locked', 1, '2012-10-31 15:00:00.0000', 5, 1, NOW(), NOW());
