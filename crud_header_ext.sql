DELIMITER $$
USE `CIS`$$
DROP TRIGGER /*!50032 IF EXISTS */ `tr_crud_header_ext_afdel`$$

CREATE
    /*!50017 DEFINER = 'root'@'%' */
    TRIGGER `tr_crud_header_ext_afdel`
    AFTER DELETE
    ON `crud_header_ext`
    FOR EACH ROW

BEGIN
    DECLARE var_userid INT;

    SET var_userid = udf_get_userid();
    SET @resource_id = NULL;

    SELECT resource_id
    INTO @resource_id
    FROM CIS.crud_header
    WHERE crud_id = old.crud_id
      AND active = 'Y';

    INSERT INTO CIS.pd_job_delete( db_name
                                 , table_name
                                 , pk1_value
                                 , pk2_value
                                 , pk3_value
                                 , entry_id
                                 , entry_datetime)
    VALUES ('CIS', 'crud_header_ext', old.oid, null, null, var_userid, NOW());

    INSERT INTO LOG.change_log_crud( crud_id
                                   , table_name
                                   , log_type
                                   , crud_detail_id
                                   , column_name
                                   , old_value
                                   , new_value
                                   , entry_id
                                   , entry_datetime)
    VALUES (old.crud_id, 'crud_header_ext', 'delete', old.oid, 'delete',
            CONCAT('crud_id=', IFNULL(old.crud_id, 'null'), 'skip_update_sql=', IFNULL(old.skip_update_sql, 'null'),
                   'skip_insert_sql=', IFNULL(old.skip_insert_sql, 'null'), 'skip_delete_sql=',
                   IFNULL(old.skip_delete_sql, 'null'), 'skip_query_sql=', IFNULL(old.skip_query_sql, 'null'),
                   'pageable=', IFNULL(old.pageable, 'null'), 'bpm_submit_label=', IFNULL(old.bpm_submit_label, 'null'),
                   'solr_collection=', IFNULL(old.solr_collection, 'null'), 'fuzzy_query_tips=',
                   IFNULL(old.fuzzy_query_tips, 'null'), 'query_panel_display=',
                   IFNULL(old.query_panel_display, 'null'), 'disable_action_log=',
                   IFNULL(old.disable_action_log, 'null'), 'disable_global_log=',
                   IFNULL(old.disable_global_log, 'null')), NULL, CIS.udf_get_userid(), NOW());

    UPDATE CIS.spy_resource1
    SET activeFlag = '1'
    WHERE resource_id = IFNULL(@resource_id, '-999901')
      AND activeFlag != '1';
END;
$$

DELIMITER;