package com.example.Braclet.Service;

import com.example.Braclet.Entity.*;
import com.example.Braclet.Repository.SensorRepository;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {
    protected final SensorRepository sensorRepository;
    public SensorDataService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public List<SensorData> getSensorDataByType(@NotNull String typeName) {
        Class<? extends SensorData> sensorClass = switch (typeName.toLowerCase()) {
            case "heartrate" -> HeartRate.class;
            case "temp" -> Temperature.class;
            case "oxygen" -> Oxygen.class;
            case "chute" -> Chute.class;
            default -> throw new IllegalArgumentException("Unknown sensor type: " + typeName);
        };

        return sensorRepository.findBySensorType(sensorClass);
    }
    public void deleteSensor(int sensorId) {
        sensorRepository.findById(sensorId);
        boolean exists = sensorRepository.existsById(sensorId);
        if (exists) {
            sensorRepository.deleteById(sensorId);
        } else {
            throw new IllegalStateException("sensorData with " + sensorId + " not found");
        }
    }

    public void addRealTimeValue(Long sensorId, Float newValue) {
        Optional<SensorData> sensorDataOpt = sensorRepository.findById(sensorId);

        if (sensorDataOpt.isPresent()) {
            SensorData sensorData = sensorDataOpt.get();
            sensorData.getRealTimeValues().add(newValue);
            sensorRepository.save(sensorData);
        } else {
            throw new RuntimeException("Sensor data not found for ID: " + sensorId);
        }
    }
    public List<Float> getRealTimeValues(Long sensorId) {
        Optional<SensorData> sensorDataOpt = sensorRepository.findById(sensorId);

        if (sensorDataOpt.isPresent()) {
            return sensorDataOpt.get().getRealTimeValues(); // Return real-time values
        } else {
            throw new RuntimeException("Sensor data not found for ID: " + sensorId);
        }
    }
    public void updateRealTimeValues(Long sensorId, List<Float> newValues) {
        Optional<SensorData> sensorDataOpt = sensorRepository.findById(sensorId);

        if (sensorDataOpt.isPresent()) {
            SensorData sensorData = sensorDataOpt.get();
            sensorData.setRealTimeValues(newValues);
            sensorRepository.save(sensorData);
        } else {
            throw new RuntimeException("Sensor data not found for ID: " + sensorId);
        }
    }

}
