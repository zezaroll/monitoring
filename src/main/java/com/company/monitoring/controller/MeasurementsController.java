package com.company.monitoring.controller;

import com.company.monitoring.dto.CreateMeasurementRequestDTO;
import com.company.monitoring.dto.CreateMeasurementResponseDTO;
import com.company.monitoring.dto.GetUserMeasurementsResponseDTO;
import com.company.monitoring.exception.InvalidMeasurementException;
import com.company.monitoring.exception.UserNotFoundException;
import com.company.monitoring.service.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/measurement")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @PostMapping()
    public ResponseEntity<CreateMeasurementResponseDTO> insertUserMeasurement(@RequestBody CreateMeasurementRequestDTO measurement) {
        CreateMeasurementResponseDTO createMeasurementResponseDTO;
        try {
            createMeasurementResponseDTO = measurementsService.addMeasurement(measurement);
            return new ResponseEntity<>(createMeasurementResponseDTO, HttpStatus.CREATED);

            //TODO: provide generic method for error handling or use ControllerAdvice
        } catch (InvalidMeasurementException e) {
            CreateMeasurementResponseDTO response = new CreateMeasurementResponseDTO();
            response.setError(true);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((response));

        } catch (UserNotFoundException e) {
            CreateMeasurementResponseDTO response = new CreateMeasurementResponseDTO();
            response.setError(true);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((response));

        }
    }

    @GetMapping("/user/{id}")
    //TODO add pagination
    public ResponseEntity<GetUserMeasurementsResponseDTO> getUserMeasurements(@PathVariable(value = "id") Long userId) {
        try {
            return ResponseEntity.ok(measurementsService.getUserMeasurements(userId));
        } catch (UserNotFoundException e) {

            GetUserMeasurementsResponseDTO response = new GetUserMeasurementsResponseDTO();
            response.setError(true);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((response));
        }
    }
}