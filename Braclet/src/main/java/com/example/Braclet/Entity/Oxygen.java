package com.example.Braclet.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "oxygen")
@DiscriminatorValue("oxygen")
public class Oxygen extends SensorData {
}
