package com.example.Braclet.Repository;

import com.example.Braclet.Entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<SensorData, Integer> {
    Optional<SensorData> findById(long id);
    Optional<SensorData> findByDate(LocalDateTime date);
    @Query("SELECT s FROM SensorData s WHERE TYPE(s) = :sensorType")
    List<SensorData> findBySensorType(@Param("sensorType") Class<? extends SensorData> sensorType);
}
