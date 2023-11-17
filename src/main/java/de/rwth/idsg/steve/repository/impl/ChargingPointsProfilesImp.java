package de.rwth.idsg.steve.repository.impl;

import de.rwth.idsg.steve.repository.ChargingPointsProfilesRepository;
import de.rwth.idsg.steve.repository.dto.ChargePointProfile;
import de.rwth.idsg.steve.web.dto.CpProfilesQueryForm;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.jooq.Result;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.steve.db.tables.ChargeBoxProfile.CHARGE_BOX_PROFILE;

@Slf4j
@Repository
public class ChargingPointsProfilesImp implements ChargingPointsProfilesRepository {
    @Autowired
    private DSLContext ctx;
    @Override
    public List<ChargePointProfile.Overview> getOverview(CpProfilesQueryForm form) {
        return getOverviewInternal(form)
                .map(r -> ChargePointProfile.Overview.builder()
                        .charge_box_id(r.value1())
                        .charging_profile_pk(r.value2())
                        .charging_profile_purpose(r.value3())
                        .stack_level(r.value4())
                        .description(r.value5())
                        .charging_rate_unit(r.value6())
                        .build()
                );
    }
    private Result<Record6<String, Integer, String, Integer, String, String>> getOverviewInternal(CpProfilesQueryForm form) {
        SelectQuery selectQuery = ctx.selectQuery();
        selectQuery.addFrom(CHARGE_BOX_PROFILE);
        selectQuery.addSelect(
                CHARGE_BOX_PROFILE.CHARGE_BOX_ID,
                CHARGE_BOX_PROFILE.CHARGING_PROFILE_PK,
                CHARGE_BOX_PROFILE.CHARGING_PROFILE_PURPOSE,
                CHARGE_BOX_PROFILE.STACK_LEVEL,
                CHARGE_BOX_PROFILE.DESCRIPTION,
                CHARGE_BOX_PROFILE.CHARGING_RATE_UNIT
        );
        return selectQuery.fetch();
    }
}
