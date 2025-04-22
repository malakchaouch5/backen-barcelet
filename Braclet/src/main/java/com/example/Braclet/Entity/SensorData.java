package com.example.Braclet.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name ="SensorData")
@Table
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(nullable = false,updatable = false)
    protected LocalDateTime date=LocalDateTime.now();
    @Column(nullable = false)
    protected float minMeasureInterval;
    @Column(nullable = false)
    protected float maxMeasureInterval;
    @ElementCollection
    protected List<Float> realTimeValues = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bracelet_id")
    private Bracelet bracelet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getMinMeasureInterval() {
        return minMeasureInterval;
    }

    public void setMinMeasureInterval(float minMeasureInterval) {
        this.minMeasureInterval = minMeasureInterval;
    }

    public float getMaxMeasureInterval() {
        return maxMeasureInterval;
    }

    public void setMaxMeasureInterval(float maxMeasureInterval) {
        this.maxMeasureInterval = maxMeasureInterval;
    }

    public List<Float> getRealTimeValues() {
        return realTimeValues;
    }

    public void setRealTimeValues(List<Float> realTimeValues) {
        this.realTimeValues = realTimeValues;
    }

    public Bracelet getBracelet() {
        return bracelet;
    }

    public void setBracelet(Bracelet bracelet) {
        this.bracelet = bracelet;
    }
}
