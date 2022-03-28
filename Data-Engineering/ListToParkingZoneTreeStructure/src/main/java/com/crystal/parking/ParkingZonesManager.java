package com.crystal.parking;

import com.crystal.entity.Vehicle;
import com.crystal.exceptions.ZoneNotFoundException;
import com.crystal.stat.ParkStatus;
import com.crystal.tree.ParkTree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ParkingZonesManager {
    private List<ParkTree> parkingZones;

    public void addParkZone (ParkTree parkTree) {
        parkingZones.add(parkTree);
    }

    public void displayParkZones() {
        StringBuilder parkZonesString = new StringBuilder();
        for (ParkTree p: parkingZones) {
            parkZonesString.append(p.toString() + "\n\n");
        }
        System.out.println(parkZonesString.toString());
    }

    public void addVehiclesArrayToParkZone(JSONArray vehiclesArray) {
        for (int i = 0; i < vehiclesArray.length(); i++) {
            JSONObject vehicleJsonObject = vehiclesArray.getJSONObject(i);

            if (ParkStatus.valueOf(vehicleJsonObject.getString("status")).equals(ParkStatus.OK)) {
                try {
                    addVehicleToParkZone(vehicleJsonObject);
                } catch (ZoneNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Status error");
            }
        }
    }

    public void addVehicleToParkZone(JSONObject vehicleJsonObject) throws ZoneNotFoundException {
        JSONArray vehicleZonePathArray = vehicleJsonObject.getJSONArray("zone");

        List<String> zonePath = new ArrayList<String>();
        for (int i = 0; i < vehicleZonePathArray.length(); i++) {
            zonePath.add(vehicleZonePathArray.getJSONObject(i).getString("zoneId"));
        }

        // Fetching the level 1 zone of the vehicle
        ParkTree zoneToAddVehicleTo = parkingZones.get(getIndexOfParkTreeZoneByName(parkingZones, zonePath.get(0)));

        // If the zone we are looking for isn't a level 1 zone
        if (zonePath.size() > 1) {
            for (int i = 1; i < zonePath.size(); i++) {
                int zoneIndex = getIndexOfParkTreeZoneByName(zoneToAddVehicleTo.getSubZones(), zonePath.get(i));
                if (zoneIndex >= 0) {
                    zoneToAddVehicleTo = zoneToAddVehicleTo.getSubZones().get(zoneIndex);
                }
                else {
                    throw new ZoneNotFoundException("Zone " + zonePath.get(i) + " could not be found...");
                }

            }
        }

        // Adding the vehicle to the zone we fetched by traversing the zonePath
        zoneToAddVehicleTo.getVehicles().add(Vehicle.getVehicleObjectFromJsonObject(vehicleJsonObject));
    }

    public int getIndexOfParkTreeZoneByName(List<ParkTree> parkingZones, String parkingZoneName) {
        for(int i = 0; i < parkingZones.size(); i++) {
            if (parkingZones.get(i).getZoneName().equals(parkingZoneName)) {
                return i;
            }
        }
        return -1;
    }
}
