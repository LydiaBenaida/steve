package de.rwth.idsg.steve.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CpProfilesQueryForm {
    private  String charge_box_id;
    private  int charging_profile_pk;
    private  String charging_profile_purpose;
    private  int stack_level;
    private  String description;
    private  String charging_rate_unit;
}
