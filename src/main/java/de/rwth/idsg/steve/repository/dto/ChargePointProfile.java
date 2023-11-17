package de.rwth.idsg.steve.repository.dto;

import lombok.Builder;
import lombok.Getter;

public class ChargePointProfile {
    @Getter
    @Builder
    public static final class Overview {
        private final String charge_box_id;
        private final int charging_profile_pk;
        private final String charging_profile_purpose;
        private final int stack_level;
        private final String description;
        private final String charging_rate_unit;
    }
}
