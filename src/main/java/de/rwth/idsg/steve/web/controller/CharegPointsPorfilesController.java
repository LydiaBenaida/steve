package de.rwth.idsg.steve.web.controller;

import de.rwth.idsg.steve.repository.ChargingPointsProfilesRepository;
import de.rwth.idsg.steve.web.dto.CpProfilesQueryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/manager/CpProfiles")
public class CharegPointsPorfilesController {
    @Autowired
    private ChargingPointsProfilesRepository chargingPointsProfilesRepository;
    private static final String PARAMS = "params";
    // -------------------------------------------------------------------------
    // Paths
    // -------------------------------------------------------------------------
    private static final String QUERY_PATH = "/query";
    @RequestMapping(method = RequestMethod.GET)
    public String getOverview(Model model) {
        initList(model, new CpProfilesQueryForm());
        return "data-man/ChargePointsProfiles";
    }

    @RequestMapping(value = QUERY_PATH, method = RequestMethod.GET)
    public String getQuery(@ModelAttribute(PARAMS) CpProfilesQueryForm params, Model model) {
        initList(model, params);
        return "data-man/ChargePointsProfiles";
    }
    private void initList(Model model, CpProfilesQueryForm params) {
            model.addAttribute(PARAMS, params);
            model.addAttribute("profilesList", chargingPointsProfilesRepository.getOverview(params));
        }


}
