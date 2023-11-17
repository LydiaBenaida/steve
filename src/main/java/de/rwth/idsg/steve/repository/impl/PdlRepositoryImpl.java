package de.rwth.idsg.steve.repository.impl;

import de.rwth.idsg.steve.SteveException;
import de.rwth.idsg.steve.repository.PdlRepository;
import de.rwth.idsg.steve.repository.dto.Pdl;
import de.rwth.idsg.steve.web.dto.PdlForm;
import de.rwth.idsg.steve.web.dto.PdlQueryForm;
import jooq.steve.db.tables.records.PdlRecord;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.SelectQuery;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.steve.db.tables.Pdl.PDL;

@Slf4j
@Repository
public class PdlRepositoryImpl implements PdlRepository {
    @Autowired
    private DSLContext ctx;
    @Override
    public List<Pdl.Overview> getOverview(PdlQueryForm form) {
        return getOverviewInternal(form)
                .map(r -> Pdl.Overview.builder()
                        .ref_PDL(r.value1())
                        .station_pk(r.value2())
                        .power(r.value3())
                        .dateActivation(r.value4())
                        .build()
                );
    }

    @Override
    public Pdl.Details getDetails(int ref_PDL) {
        // -------------------------------------------------------------------------
        // 1. pdl table
        // -------------------------------------------------------------------------

        PdlRecord ur = ctx.selectFrom(PDL)
                .where(PDL.REF_PDL.equal(ref_PDL))
                .fetchOne();

        if (ur == null) {
            throw new SteveException("There is no pdl with id '%s'", ref_PDL);
        }


        return Pdl.Details.builder()
                .pdlRecord(ur)
                .build();
    }

    private Result<Record4<Integer, String, String, DateTime>> getOverviewInternal(PdlQueryForm form) {
        SelectQuery selectQuery = ctx.selectQuery();
        selectQuery.addFrom(PDL);
        selectQuery.addSelect(
                PDL.REF_PDL,
                PDL.STATION_PK,
                PDL.POWER,
                PDL.DATEACTIVATION
        );



        return selectQuery.fetch();
    }

    @Override
    public void add(PdlForm form) {
        ctx.transaction(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            try {
                addInternal(ctx, form);

            } catch (DataAccessException e) {
                throw new SteveException("Failed to add the user", e);
            }
        });
    }

    @Override
    public void update(PdlForm form) {
        ctx.transaction(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            try {
                updateInternal(ctx, form);

            } catch (DataAccessException e) {
                throw new SteveException("Failed to update the user", e);
            }
        });
    }

    @Override
    public void delete(int refPDL) {
        ctx.transaction(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            try {
                deleteInternal(ctx, refPDL);

            } catch (DataAccessException e) {
                throw new SteveException("Failed to delete the user", e);
            }
        });
    }
    private void addInternal(DSLContext ctx, PdlForm form) {
        int count = ctx.insertInto(PDL)
                .set(PDL.STATION_PK, form.getStation_pk())
                .set(PDL.POWER, form.getPower())
                .execute();

        if (count != 1) {
            throw new SteveException("Failed to insert the user");
        }
    }
    private void updateInternal(DSLContext ctx, PdlForm form) {
        ctx.update(PDL)
                .set(PDL.STATION_PK, form.getStation_pk())
                .set(PDL.POWER, form.getPower())
                .where(PDL.REF_PDL.eq(form.getRef_PDL()))
                .execute();
    }

    private void deleteInternal(DSLContext ctx, int refPDL) {
        ctx.delete(PDL)
                .where(PDL.REF_PDL.equal(refPDL))
                .execute();
    }

}
