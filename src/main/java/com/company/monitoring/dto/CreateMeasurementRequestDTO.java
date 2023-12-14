package com.company.monitoring.dto;

import com.company.monitoring.enums.WaterType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMeasurementRequestDTO {

    @NotNull
    @Schema(description = "User ID", example = "1")
    private long userId;

    @NotNull
    @Schema(description = "Volume of water measured", example = "10.5")
    private double water;

    @NotNull
    @Schema(description = "Type of water (COLD or HOT)", example = "COLD")
    private WaterType waterType;

    @NotNull
    @Schema(description = "Amount of gas measured", example = "25.0")
    private double gas;
}
