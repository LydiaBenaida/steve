/*
 * SteVe - SteckdosenVerwaltung - https://github.com/steve-community/steve
 * Copyright (C) 2013-2023 SteVe Community Team
 * All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.rwth.idsg.steve.utils.mapper;

import de.rwth.idsg.steve.repository.dto.Pdl;
import de.rwth.idsg.steve.web.dto.PdlForm;
import jooq.steve.db.tables.records.PdlRecord;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PdlFormMapper {

    public static PdlForm toForm(Pdl.Details details) {
        PdlRecord pdlRecord = details.getPdlRecord();

        PdlForm form = new PdlForm();
        form.setRef_PDL(pdlRecord.getRefPdl());
        form.setStation_pk(pdlRecord.getStationPk());
        form.setPower(pdlRecord.getPower());
        form.setDateActivation(pdlRecord.getDateactivation().toLocalDateTime());

        return form;
    }
}
