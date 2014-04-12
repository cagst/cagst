UPDATE cgt_user
   SET last_signin_dt_tm = UTC_TIMESTAMP()
 WHERE cgt_user_id = :cgt_user_id