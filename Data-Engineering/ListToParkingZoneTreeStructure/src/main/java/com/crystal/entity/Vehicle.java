package com.crystal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@AllArgsConstructor
@ToString
@Getter
public class Vehicle {
    private final String vin;

    public static Vehicle getVehicleObjectFromJsonObject(JSONObject vehicleJsonObject) {
        return new Vehicle(vehicleJsonObject.getString("vin"));
    }
}
