UPDATE cgt_user
   SET password = SHA1(:password)
      ,change_password_ind = 0
 WHERE cgt_user_id = :cgt_user_id