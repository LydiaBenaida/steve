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
package de.rwth.idsg.steve.web.controller;

import de.rwth.idsg.steve.repository.PdlRepository;
import de.rwth.idsg.steve.repository.dto.Pdl;
import de.rwth.idsg.steve.utils.mapper.PdlFormMapper;
import de.rwth.idsg.steve.web.dto.PdlForm;
import de.rwth.idsg.steve.web.dto.PdlQueryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Sevket Goekay <sevketgokay@gmail.com>
 * @since 25.11.2015
 */
@Controller
@RequestMapping(value = "/manager/pdls")
public class PdlsController {


    @Autowired private PdlRepository pdlRepository;
    private static final String PARAMS = "params";

    // -------------------------------------------------------------------------
    // Paths
    // -------------------------------------------------------------------------

    private static final String QUERY_PATH = "/query";

    private static final String DETAILS_PATH = "/details/{ref_PDL}";
    private static final String ADD_PATH = "/add";
    private static final String DELETE_PATH = "/delete/{refPDL}";
    private static final String UPDATE_PATH = "/update";
    // -------------------------------------------------------------------------
    // HTTP methods
    // -------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    public String getOverview(Model model) {
        initList(model, new PdlQueryForm());
        return "data-man/pdls";
    }

    @RequestMapping(value = QUERY_PATH, method = RequestMethod.GET)
    public String getQuery(@ModelAttribute(PARAMS) PdlQueryForm params, Model model) {
        initList(model, params);
        return "data-man/pdls";
    }
    @RequestMapping(value = DETAILS_PATH, method = RequestMethod.GET)
    public String getDetails(@PathVariable("ref_PDL") int ref_PDL, Model model) {
        Pdl.Details details = pdlRepository.getDetails(ref_PDL);
        PdlForm form = PdlFormMapper.toForm(details);

        model.addAttribute("pdlForm", form);
        return "data-man/pdlDetails";
    }
    private void initList(Model model, PdlQueryForm params) {
        model.addAttribute(PARAMS, params);
        model.addAttribute("pdlList", pdlRepository.getOverview(params));
    }


    @RequestMapping(value = ADD_PATH, method = RequestMethod.GET)
    public String addGet(Model model) {
        model.addAttribute("pdlForm", new PdlForm());
        return "data-man/pdlAdd";
    }

    @RequestMapping(params = "add", value = ADD_PATH, method = RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute("pdlForm") PdlForm pdlForm,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "data-man/pdlAdd";
        }

        pdlRepository.add(pdlForm);
        return toOverview();
    }
    @RequestMapping(params = "update", value = UPDATE_PATH, method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("pdlForm") PdlForm pdlForm,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "data-man/userDetails";
        }

        pdlRepository.update(pdlForm);
        return toOverview();
    }

    @RequestMapping(value = DELETE_PATH, method = RequestMethod.POST)
    public String delete(@PathVariable("refPDL") int refPDL) {
        pdlRepository.delete(refPDL);
        return toOverview();
    }
    // -------------------------------------------------------------------------
    // Back to Overview
    // -------------------------------------------------------------------------

    @RequestMapping(params = "backToOverview", value = ADD_PATH, method = RequestMethod.POST)
    public String addBackToOverview() {
        return toOverview();
    }

    @RequestMapping(params = "backToOverview", value = UPDATE_PATH, method = RequestMethod.POST)
    public String updateBackToOverview() {
        return toOverview();
    }

    private String toOverview() {
        return "redirect:/manager/pdls";
    }
}
