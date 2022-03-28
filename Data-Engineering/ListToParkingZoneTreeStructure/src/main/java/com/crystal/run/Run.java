package com.crystal.run;

import com.crystal.io.VehicleJSONReader;

public class Run {
    public static void main(String[] args) {
        VehicleJSONReader vehicleJSONReader = new VehicleJSONReader();

        System.out.println(vehicleJSONReader.readVehicles("src/main/resources/inputList.json"));
    }
}