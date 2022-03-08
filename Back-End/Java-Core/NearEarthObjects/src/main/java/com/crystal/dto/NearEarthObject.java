package com.crystal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class NearEarthObject {
    private final long id;
    private final String name;
    private final float estimatedDiameterMin; // in meters
    private final float estimatedDiameterMax; // in meters
    private final boolean isPotentiallyHazardous;
    private final Date closeApproachDate;
    private final float relativeVelocity; // in kilometers/second
    private final float missDistance; // in kilometers
    private final String orbitingBody;
    private final boolean isSentryObject;
}
