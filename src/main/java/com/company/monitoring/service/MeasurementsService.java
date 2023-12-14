package com.company.monitoring.service;

import com.company.monitoring.dto.CreateMeasurementRequestDTO;
import com.company.monitoring.dto.CreateMeasurementResponseDTO;
import com.company.monitoring.dto.GetUserMeasurementsResponseDTO;
import com.company.monitoring.exception.InvalidMeasurementException;
import com.company.monitoring.exception.UserNotFoundException;

public interface MeasurementsService {

    CreateMeasurementResponseDTO addMeasurement(CreateMeasurementRequestDTO createMeasureRequestDTO) throws InvalidMeasurementException, UserNotFoundException;

    GetUserMeasurementsResponseDTO getUserMeasurements(Long userId) throws UserNotFoundException;

}