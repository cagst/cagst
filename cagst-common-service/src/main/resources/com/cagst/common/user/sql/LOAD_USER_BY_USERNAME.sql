SELECT cu.cgt_user_id, cu.cgt_person_id, cu.username, cu.password, cu.expire_dt_tm, cu.change_password_ind
      ,cu.last_signin_dt_tm, cu.signin_attempts, cu.account_locked_dt_tm, cu.active_ind, cu.updt_cnt, cu.updt_dt_tm
      ,cp.name_last, cp.name_first, cp.name_middle, cp.dob_dt_tm
  FROM cgt_user cu, cgt_person cp
 WHERE cu.username = :username
   AND cu.active_ind = 1
   AND cp.cgt_person_id = cu.cgt_person_id
