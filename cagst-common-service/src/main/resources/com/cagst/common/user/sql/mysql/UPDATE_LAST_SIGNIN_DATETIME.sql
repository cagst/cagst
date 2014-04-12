UPDATE cgt_user
   SET last_signin_dt_tm = UTC_TIMESTAMP()
      ,signin_attempts   = 0
 WHERE cgt_user_id = :cgt_user_id