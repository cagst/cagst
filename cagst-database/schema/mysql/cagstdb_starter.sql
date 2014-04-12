-- Insert default User
INSERT INTO cgt_person (cgt_person_id, name_last, name_last_key, name_first, name_first_key, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 'System', 'SYSTEM', 'System', 'SYSTEM', 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_user (cgt_user_id, cgt_person_id, username, password, change_password_ind, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 1, 'system', SHA('cagstsolutions'), 0, 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());

-- Insert CodeSets
INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 'State', 'STATE', 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codeset (cgt_codeset_id, codeset_display, codeset_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (2, 'Gender', 'GENDER', 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());

-- Insert States
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 1, "Alabama", "AL", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 2, "Alaska", "AK", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 3, "Arizona", "AZ", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 4, "Arkansas", "AR", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 5, "California", "CA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 6, "Colorado", "CO", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 7, "Connecticut", "CT", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 8, "Delaware", "DE", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 9, "Florida", "FL", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 10, "Georgia", "GA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 11, "Hawaii", "HI", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 12, "Idaho", "ID", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 13, "Illinois", "IL", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 14, "Indiana", "IN", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 15, "Iowa", "IA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 16, "Kansas", "KS", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 17, "Kentucky", "KY", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 18, "Louisiana", "LA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 19, "Maine", "ME", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 20, "Maryland", "MD", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 21, "Massachusetts", "MA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 22, "Michigan", "MI", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 23, "Minnesota", "MN", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 24, "Mississippi", "MS", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 25, "Missouri", "MO", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 26, "Montana", "MT", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 27, "Nebraska", "NE", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 28, "Nevada", "NV", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 29, "New Hampshire", "NH", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 30, "New Jersey", "NJ", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 31, "New Mexico", "NM", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 32, "New York", "NY", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 33, "North Carolina", "NC", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 34, "North Dakota", "ND", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 35, "Ohio", "OH", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 36, "Oklahoma", "OK", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 37, "Oregon", "OR", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 38, "Pennsylvania", "PA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 39, "Rhode Island", "RI", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 40, "South Carolina", "SC", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 41, "South Dakota", "SD", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 42, "Tennessee", "TN", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 43, "Texas", "TX", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 44, "Utah", "UT", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 45, "Vermont", "VT", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 46, "Virginia", "VA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 47, "Washington", "WA", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 48, "West Virginia", "WV", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 49, "Wisconsin", "WI", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (1, 50, "Wyoming", "WY", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());

-- Insert Genders
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (2, 51, "Unknown", "UNKNOWN", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (2, 52, "Male", "MALE", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
INSERT INTO cgt_codevalue (cgt_codeset_id, cgt_codevalue_id, codevalue_display, codevalue_meaning, create_id, create_dt_tm, updt_id, updt_dt_tm) VALUES (2, 53, "Female", "Female", 1, UTC_TIMESTAMP(), 1, UTC_TIMESTAMP());
