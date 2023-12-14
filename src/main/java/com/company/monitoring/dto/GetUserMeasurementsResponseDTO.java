package com.company.monitoring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserMeasurementsResponseDTO {
    @Schema(description = "User ID", example = "1")
    private long userId;

    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "List of measurement results")
    private List<MeasurementResult> measurementResult;

    @Schema(description = "Flag indicating if there is an error")
    private boolean error;

    @Schema(description = "Error message")
    private String message;
}
