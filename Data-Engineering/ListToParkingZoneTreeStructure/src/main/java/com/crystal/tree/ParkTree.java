package com.crystal.tree;

import com.crystal.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ParkTree {
    private String zoneName;
    private int zoneLevel;
    @Builder.Default
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();
    @Builder.Default
    private List<ParkTree> subZones = null;
    @Builder.Default
    private boolean isLowestLevelZone = false;

    // Constructor with zone name and sub zone list specified, thus it is not the lowest level zone in this tree
    public ParkTree(String zoneName, int zoneLevel, List<ParkTree> subZones) {
        this.zoneName = zoneName;
        this.zoneLevel = zoneLevel;
        this.vehicles = new ArrayList<Vehicle>();
        this.subZones = subZones;
        this.isLowestLevelZone = false;
    }

    // Constructor with only zone name specified, this implies that there is no subsequent sub zone, thus it is the lowest level zone in this tree
    public ParkTree(String zoneName, int zoneLevel) {
        this.zoneName = zoneName;
        this.zoneLevel = zoneLevel;
        this.vehicles = new ArrayList<Vehicle>();
        this.subZones = null;
        this.isLowestLevelZone = true;
    }

    public String toString() {
        StringBuilder parkTreeString = new StringBuilder();
        StringBuilder indentationBuilder = new StringBuilder("");
        for(int i = 0; i < zoneLevel - 1 ; i++) {
            indentationBuilder.append("  ");
        }
        String indentation = indentationBuilder.toString();

        parkTreeString.append(indentation + zoneName + ": ");

        if (vehicles.size() > 0) {
            for (Vehicle v: vehicles) {
                parkTreeString.append(v.getVin() + " ");
            }
        }

        parkTreeString.append("\n");

        if (!isLowestLevelZone) {
            for (ParkTree p: subZones) {
                parkTreeString.append(p);
            }
        }

        return parkTreeString.toString();
    }
}
