package de.rwth.idsg.steve.repository;

import de.rwth.idsg.steve.repository.dto.ChargePointProfile;
import de.rwth.idsg.steve.web.dto.CpProfilesQueryForm;

import java.util.List;

public interface ChargingPointsProfilesRepository {
    List<ChargePointProfile.Overview> getOverview(CpProfilesQueryForm form);
}
