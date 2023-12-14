package com.company.monitoring.service;

import com.company.monitoring.dto.CreateMeasurementRequestDTO;
import com.company.monitoring.dto.CreateMeasurementResponseDTO;
import com.company.monitoring.dto.GetUserMeasurementsResponseDTO;
import com.company.monitoring.dto.MeasurementResult;
import com.company.monitoring.entity.Measurement;
import com.company.monitoring.entity.User;
import com.company.monitoring.exception.InvalidMeasurementException;
import com.company.monitoring.exception.UserNotFoundException;
import com.company.monitoring.repository.MeasurementsRepository;
import com.company.monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class MeasurementsServiceImpl implements MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final UserRepository userRepository;

    private static final String EQUAL_OR_LESS_THAN_ZERO_MESSAGE = "%s couldn't be equal or less than zero";
    private static final String USER_NOT_FOUND_MESSAGE = "User with ID:[%d] not found";

    @Autowired
    public MeasurementsServiceImpl(MeasurementsRepository measurementsRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.measurementsRepository = measurementsRepository;
    }

    @Override
    @Transactional
    //TODO create more suitable logic so @Transactional made more sense
    public CreateMeasurementResponseDTO addMeasurement(CreateMeasurementRequestDTO measureRequestDTO)
            throws InvalidMeasurementException, UserNotFoundException {
        validateMeasurement(measureRequestDTO);

        Long userId = measureRequestDTO.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));

        //Let assume we could store only Cold or Hot water in a time
        Measurement measurement = new Measurement();
        measurement.setUser(user);
        measurement.setWaterType(measureRequestDTO.getWaterType());
        measurement.setWater(measureRequestDTO.getWater());
        measurement.setGas(measureRequestDTO.getGas());

        measurement = measurementsRepository.save(measurement);

        return CreateMeasurementResponseDTO.builder()
                .userId(userId)
                .gas(measurement.getGas())
                .water(measurement.getWater())
                .waterType(measurement.getWaterType())
                .build();
    }

    @Override
    public GetUserMeasurementsResponseDTO getUserMeasurements(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));

        List<Measurement> measurements = measurementsRepository.findByUserId(user.getId());
        List<MeasurementResult> collect = measurements.stream()
                .map(c -> new MeasurementResult(c.getWater(), c.getWaterType(), c.getGas()))
                .collect(Collectors.toList());

        return GetUserMeasurementsResponseDTO.builder()
                .userId(userId)
                .firstName(user.getFirstName())
                .measurementResult(collect)
                .build();
    }

    private void validateMeasurement(CreateMeasurementRequestDTO measurement) throws InvalidMeasurementException {
        // TODO: Possible solution - move error messages in separate list of Errors for providing all issues at once
        if (measurement.getUserId() <= 0) {
            //UserID 0 just considered as bad practice
            throw new InvalidMeasurementException(String.format(EQUAL_OR_LESS_THAN_ZERO_MESSAGE, "UserID"));
        }
        if (measurement.getWater() <= 0) {
            throw new InvalidMeasurementException(String.format(EQUAL_OR_LESS_THAN_ZERO_MESSAGE, "Water volume"));
        }
        if (measurement.getGas() <= 0) {
            throw new InvalidMeasurementException(String.format(EQUAL_OR_LESS_THAN_ZERO_MESSAGE, "Gas volume"));
        }
    }
}