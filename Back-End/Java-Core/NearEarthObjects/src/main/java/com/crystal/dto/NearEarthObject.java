package com.crystal.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class NearEarthObject {
    private final long id;
    private final String name;
    private final float estimatedDiameterMin; // in meters
    private final float estimatedDiameterMax; // in meters
    private final boolean isPotentiallyHazardous;
    private final String closeApproachDate;
    private final float relativeVelocity; // in kilometers/second
    private final float missDistance; // in astronomical units (unit of length effectively equal to the average,
                                      // or mean, distance between Earth and the Sun, defined as 149,597,870.7 km)
    private final String orbitingBody;
    private final boolean isSentryObject;
}
