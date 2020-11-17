DELIMITER $$
USE `CIS`$$
DROP TRIGGER /*!50032 IF EXISTS */ `tr_crud_detail_ext_afdel`$$

CREATE
    /*!50017 DEFINER = 'root'@'%' */
    TRIGGER `tr_crud_detail_ext_afdel`
    AFTER DELETE
    ON `crud_detail_ext`
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
    VALUES ('CIS', 'crud_detail_ext', old.oid, null, null, var_userid, NOW());

    INSERT INTO LOG.change_log_crud( crud_id
                                   , table_name
                                   , log_type
                                   , crud_detail_id
                                   , column_name
                                   , old_value
                                   , new_value
                                   , entry_id
                                   , entry_datetime)
    VALUES (old.crud_id, 'crud_detail_ext', 'delete', old.oid, 'delete',
            CONCAT('crud_id=', IFNULL(old.crud_id, 'null'), 'column_name=', IFNULL(old.column_name, 'null'), 'scale=',
                   IFNULL(old.scale, 'null'), 'min_width=', IFNULL(old.min_width, 'null'), 'max_width=',
                   IFNULL(old.max_width, 'null'), 'auto_complete=', IFNULL(old.auto_complete, 'null'), 'criteria_tip=',
                   IFNULL(old.criteria_tip, 'null'), 'solr_field=', IFNULL(old.solr_field, 'null'), 'solr_indexed=',
                   IFNULL(old.solr_indexed, 'null'), 'layout_row_num=', IFNULL(old.layout_row_num, 'null'),
                   'criteria_width=', IFNULL(old.criteria_width, 'null'), 'qgen_tab=', IFNULL(old.qgen_tab, 'null'),
                   'source_limit=', IFNULL(old.source_limit, 'null')), NULL, CIS.udf_get_userid(), NOW());

    UPDATE CIS.spy_resource1
    SET activeFlag = '1'
    WHERE resource_id = IFNULL(@resource_id, '-999901')
      AND activeFlag != '1';
END;
$$

DELIMITER;