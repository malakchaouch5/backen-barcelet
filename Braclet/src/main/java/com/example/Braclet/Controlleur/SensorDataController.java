package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.SensorData;
import com.example.Braclet.Service.SensorDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/sensorData")
@CrossOrigin("http://localhost:5173")
public class SensorDataController {
    protected final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }
    @GetMapping("/{type}")
    public ResponseEntity<List<SensorData>> getSensorData(@PathVariable String type) {
        return ResponseEntity.ok(sensorDataService.getSensorDataByType(type));
    }
    @GetMapping(path = "{sensorId}")
    public List<Float> getRealTimeValues(@PathVariable Long sensorId) {
        return sensorDataService.getRealTimeValues(sensorId);
    }
    @PostMapping(path = "/post/{sensorId}")
    public void addRealTimeValue(@PathVariable Long sensorId, @RequestBody Float newValue) {
        sensorDataService.addRealTimeValue(sensorId,newValue);
    }
    @DeleteMapping(path = "/del/{sensorId}")
    public  void deleteSensor(@PathVariable int sensorId) {
        sensorDataService.deleteSensor(sensorId);
    }
    @PutMapping(path = "{sensorId}")
    public void updateRealTimeValues(@PathVariable("sensorId") Long sensorId,
                                     @RequestParam(required = false) List<Float> newValues) {
        sensorDataService.updateRealTimeValues(sensorId, newValues);
    }
}