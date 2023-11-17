CREATE OR REPLACE VIEW charge_box_profile AS
    SELECT
        cb.charge_box_id,
        ccp.charging_profile_pk,
        cp.charging_profile_purpose,
        cp.stack_level,
        cp.description AS description,  -- Note: Corrected the spelling mistake in "description"
        cp.charging_rate_unit
    FROM
        connector c
            JOIN
        connector_charging_profile ccp ON c.connector_pk = ccp.connector_pk
            JOIN
        charging_profile cp ON ccp.charging_profile_pk = cp.charging_profile_pk
            JOIN
        charge_box cb ON c.charge_box_id = cb.charge_box_id;

COMMIT;