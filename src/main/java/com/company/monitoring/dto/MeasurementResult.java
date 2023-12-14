package com.company.monitoring.dto;

import com.company.monitoring.enums.WaterType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MeasurementResult {
    @Schema(description = "Volume of water measured", example = "10.5")
    private double water;

    @Schema(description = "Type of water (COLD or HOT)")
    private WaterType waterType;

    @Schema(description = "Amount of gas measured", example = "5.3")
    private double gas;
}
