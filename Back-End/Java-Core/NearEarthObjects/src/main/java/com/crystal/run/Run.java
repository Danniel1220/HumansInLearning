package com.crystal.run;

import com.crystal.api.NearEarthObjectAPIManager;
import com.crystal.parse.NeoJsonParser;

public class Run {

    public static void main(String[] args) {
        NearEarthObjectAPIManager nearEarthObjectAPIManager = new NearEarthObjectAPIManager();

        System.out.println(NeoJsonParser.parseNeoJsonToNeoList(nearEarthObjectAPIManager.getDemoKeyAPIResponse()));
    }
}
