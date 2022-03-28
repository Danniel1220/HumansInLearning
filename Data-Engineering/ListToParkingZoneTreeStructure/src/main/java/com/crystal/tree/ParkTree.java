package com.crystal.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ParkTree {
    private final String zoneName;
    private final ParkTree parkTree;
}
