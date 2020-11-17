DELIMITER $$
USE `CIS`$$
DROP TRIGGER /*!50032 IF EXISTS */ `tr_qgen_api_invoke_afdel`$$

CREATE
    /*!50017 DEFINER = 'root'@'%' */
    TRIGGER `tr_qgen_api_invoke_afdel`
    AFTER DELETE
    ON `qgen_api_invoke`
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
    VALUES ('CIS', 'qgen_api_invoke', old.oid, null, null, var_userid, NOW());

    INSERT INTO LOG.change_log_crud( crud_id
                                   , table_name
                                   , log_type
                                   , crud_detail_id
                                   , column_name
                                   , old_value
                                   , new_value
                                   , entry_id
                                   , entry_datetime)
    VALUES (old.crud_id, 'qgen_api_invoke', 'delete', old.oid, 'delete',
            CONCAT('api_type=', IFNULL(old.api_type, 'null'), 'api_name=', IFNULL(old.api_name, 'null'),
                   'biz_reference=', IFNULL(old.biz_reference, 'null'), 'prod_url=', IFNULL(old.prod_url, 'null'),
                   'sandbox_url=', IFNULL(old.sandbox_url, 'null'), 'service_name=', IFNULL(old.service_name, 'null'),
                   'method=', IFNULL(old.method, 'null'), 'content_type=', IFNULL(old.content_type, 'null'),
                   'auth_mode=', IFNULL(old.auth_mode, 'null'), 'swagger_url=', IFNULL(old.swagger_url, 'null'),
                   'pre_dsl=', IFNULL(old.pre_dsl, 'null'), 'post_dsl=', IFNULL(old.post_dsl, 'null')), NULL,
            CIS.udf_get_userid(), NOW());

    UPDATE CIS.spy_resource1
    SET activeFlag = '1'
    WHERE resource_id = IFNULL(@resource_id, '-999901')
      AND activeFlag != '1';
END;
$$

DELIMITER;