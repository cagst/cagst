SELECT cgt_codeset_id, codeset_display, codeset_meaning, active_ind, updt_cnt
  FROM cgt_codeset
 WHERE cgt_codeset_id = :cgt_codeset_id
