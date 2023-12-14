package com.company.monitoring.repository;

import com.company.monitoring.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByUserId(Long userId);
}
