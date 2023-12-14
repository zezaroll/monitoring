package com.company.monitoring.dto;

import com.company.monitoring.enums.WaterType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeasurementResponseDTO {

    @Schema(description = "User ID", example = "1")
    private long userId;

    @Schema(description = "Water volume", example = "10.5")
    private double water;

    @Schema(description = "Type of water (COLD/HOT)", example = "COLD")
    private WaterType waterType;

    @Schema(description = "Gas value", example = "25.0")
    private double gas;

    @Schema(description = "Flag indicating if there is an error")
    private boolean error;

    @Schema(description = "Error message")
    private String message;
}
