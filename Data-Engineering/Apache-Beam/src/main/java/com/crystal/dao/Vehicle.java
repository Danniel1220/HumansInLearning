package com.crystal.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle implements Serializable {
    private String VIN;
    private String brand;
    private String model;
    private int yearProduced;


}
