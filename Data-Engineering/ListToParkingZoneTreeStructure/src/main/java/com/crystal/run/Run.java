package com.crystal.run;

import com.crystal.entity.Vehicle;
import com.crystal.io.VehicleJSONReader;
import com.crystal.parking.ParkingZonesManager;
import com.crystal.tree.ParkTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        VehicleJSONReader vehicleJSONReader = new VehicleJSONReader();
        ParkingZonesManager parkingZonesManager = new ParkingZonesManager(new ArrayList<ParkTree>());

        // Manually creating zone from the ground up, before realising I can use Arrays.asList I had to do this
        ParkTree zoneL3_1 = new ParkTree("L3_1", 3);
        ParkTree zoneL3_2 = new ParkTree("L3_2", 3);

        List<ParkTree> zoneL3List = new ArrayList<ParkTree>();
        zoneL3List.add(zoneL3_1);
        zoneL3List.add(zoneL3_2);

        ParkTree zoneL2_1 = new ParkTree("L2_1", 2);
        ParkTree zoneL2_2 = new ParkTree("L2_2", 2, zoneL3List);

        List<ParkTree> zone2List = new ArrayList<ParkTree>();
        zone2List.add(zoneL2_1);
        zone2List.add(zoneL2_2);

        ParkTree zoneL1_1 = new ParkTree("L1_1", 1, zone2List);

        // Using a builder instead
        ParkTree zoneWithBuilder = ParkTree.builder()
                .zoneName("L1_1")
                .subZones(Arrays.asList(
                        ParkTree.builder()
                                .zoneName("L2_1")
                                .isLowestLevelZone(true)
                                .build(),
                        ParkTree.builder()
                                .zoneName("L2_2")
                                .subZones(Arrays.asList(
                                        ParkTree.builder()
                                                .zoneName("L3_1")
                                                .isLowestLevelZone(true)
                                                .build(),
                                        ParkTree.builder()
                                                .zoneName("L3_2")
                                                .isLowestLevelZone(true)
                                                .build()
                                ))
                                .build()
                        )
                )
                .build();

        // Using Arrays.asList to create the tree structure on the go
        ParkTree zoneWithConstructor = new ParkTree("L1_1", 1, Arrays.asList(
                new ParkTree("L2_1", 2),
                new ParkTree("L2_2", 2, Arrays.asList(
                        new ParkTree("L3_1", 3),
                        new ParkTree("L3_2", 3)
                ))
        ));

        parkingZonesManager.addParkZone(zoneWithConstructor);

        parkingZonesManager.addVehiclesArrayToParkZone(vehicleJSONReader.readVehicles("src/main/resources/inputList.json"));

        parkingZonesManager.displayParkZones();
    }
}