package com.crystal.parking;

import com.crystal.stat.ParkStatus;
import com.crystal.tree.ParkTree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@AllArgsConstructor
@Getter
public class ParkingZonesManager {
    private List<ParkTree> parkingZones;

    public void addParkZone (ParkTree parkTree) {

    }

    public void addVehiclesArrayToParkZone(JSONArray vehiclesArray) {
        for (int i = 0; i < vehiclesArray.length(); i++) {
            JSONObject vehicleJsonObject = new JSONObject(vehiclesArray.getJSONObject(i));

            if (ParkStatus.valueOf(vehicleJsonObject.getString("status")).equals(ParkStatus.OK)) {
                System.out.println("Status OK");
            }
            else {
                System.out.println("Status error");
            }

            addVehicleToParkZone(vehicleJsonObject);
        }
    }

    public void addVehicleToParkZone(JSONObject vehicleJsonObject) {


    }
}
