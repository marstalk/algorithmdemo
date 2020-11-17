DELIMITER $$
USE `CIS`$$
DROP TRIGGER /*!50032 IF EXISTS */ `tr_qgen_service_custom_body_afdel`$$

CREATE
    /*!50017 DEFINER = 'root'@'%' */
    TRIGGER `tr_qgen_service_custom_body_afdel`
    AFTER DELETE
    ON `qgen_service_custom_body`
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
    VALUES ('CIS', 'qgen_service_custom_body', old.oid, null, null, var_userid, NOW());

    INSERT INTO LOG.change_log_crud( crud_id
                                   , table_name
                                   , log_type
                                   , crud_detail_id
                                   , column_name
                                   , old_value
                                   , new_value
                                   , entry_id
                                   , entry_datetime)
    VALUES (old.crud_id, 'qgen_service_custom_body', 'delete', old.oid, 'delete',
            CONCAT('crud_id=', IFNULL(old.crud_id, 'null'), 'api_type=', IFNULL(old.api_type, 'null'), 'request_body=',
                   IFNULL(old.request_body, 'null'), 'response_body=', IFNULL(old.response_body, 'null')), NULL,
            CIS.udf_get_userid(), NOW());

    UPDATE CIS.spy_resource1
    SET activeFlag = '1'
    WHERE resource_id = IFNULL(@resource_id, '-999901')
      AND activeFlag != '1';
END;
$$

DELIMITER;