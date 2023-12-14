package com.company.monitoring.service;

import com.company.monitoring.dto.CreateMeasurementRequestDTO;
import com.company.monitoring.dto.CreateMeasurementResponseDTO;
import com.company.monitoring.entity.Measurement;
import com.company.monitoring.entity.User;
import com.company.monitoring.enums.WaterType;
import com.company.monitoring.exception.InvalidMeasurementException;
import com.company.monitoring.exception.UserNotFoundException;
import com.company.monitoring.repository.MeasurementsRepository;
import com.company.monitoring.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementsServiceTest {

    @Mock
    private MeasurementsRepository measurementsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private final MeasurementsService service =
            new MeasurementsServiceImpl(measurementsRepository, userRepository);

    @Test
    void shouldReturnResponse_whenInputValid() throws InvalidMeasurementException, UserNotFoundException {
        CreateMeasurementRequestDTO requestDTO = new CreateMeasurementRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setWater(10.5);
        requestDTO.setWaterType(WaterType.COLD);
        requestDTO.setGas(25.0);

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(measurementsRepository.save(any(Measurement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        CreateMeasurementResponseDTO responseDTO = service.addMeasurement(requestDTO);

        //then
        assertEquals(1L, responseDTO.getUserId());
        assertEquals(10.5, responseDTO.getWater(), 0.0);
        assertEquals(WaterType.COLD, responseDTO.getWaterType());
        assertEquals(25.0, responseDTO.getGas(), 0.0);

        verify(userRepository, times(1)).findById(1L);
        verify(measurementsRepository, times(1)).save(any(Measurement.class));
    }

    @Test
    void shouldThrowsNotFoundException_whenUserNotFound() {
        CreateMeasurementRequestDTO requestDTO = new CreateMeasurementRequestDTO();
        requestDTO.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        assertThrows(UserNotFoundException.class, () -> service.addMeasurement(requestDTO));

        //then
        verify(userRepository, times(1)).findById(1L);
        verify(measurementsRepository, never()).save(any(Measurement.class));
    }

    @Test
    void shouldThrowsInvalidMeasurementException_whenMeasurementInvalid() {
        CreateMeasurementRequestDTO requestDTO = new CreateMeasurementRequestDTO();
        requestDTO.setUserId(1L);

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //when
        assertThrows(InvalidMeasurementException.class, () -> service.addMeasurement(requestDTO));

        //then
        verify(userRepository, times(1)).findById(1L);
        verify(measurementsRepository, never()).save(any(Measurement.class));
    }
}