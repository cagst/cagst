SELECT cgt_codevalue_id, cgt_codeset_id, cgt_client_id, codevalue_display, codevalue_meaning, active_ind, updt_cnt
  FROM cgt_codevalue
 WHERE cgt_codeset_id = :cgt_codeset_id
