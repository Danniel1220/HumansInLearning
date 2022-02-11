package com.crystal.dao;

import lombok.*;
import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Vehicle implements Serializable {
    private String VIN;
    private String brand;
    private String model;
    private int yearProduced;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(VIN, vehicle.VIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }
}
