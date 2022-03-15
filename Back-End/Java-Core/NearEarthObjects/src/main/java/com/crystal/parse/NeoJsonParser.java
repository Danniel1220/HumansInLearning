package com.crystal.parse;

import com.crystal.dto.NearEarthObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeoJsonParser {


    public static List<NearEarthObject> parseNeoJsonToNeoList(String inputJsonString) {
        List<NearEarthObject> nearEarthObjectList = new ArrayList<>();

        JSONObject inputJsonObject = new JSONObject(inputJsonString);

        JSONObject links = inputJsonObject.getJSONObject("links");
        int elementCount = inputJsonObject.getInt("element_count");
        JSONObject nearEarthObjects = inputJsonObject.getJSONObject("near_earth_objects");

        Iterator<String> neoDaysIterator = nearEarthObjects.keys();
        while (neoDaysIterator.hasNext()) {
            String dayKey = neoDaysIterator.next();

            JSONArray neoInADay = (JSONArray) nearEarthObjects.get(dayKey);

            for (int i = 0; i < neoInADay.length(); i++) {
                JSONObject neoJsonObject = neoInADay.getJSONObject(i);
                NearEarthObject nearEarthObject = parseNeoJsonToNeoObject(neoJsonObject.toString());
                nearEarthObjectList.add(nearEarthObject);
            }
        }

        return nearEarthObjectList;
    }

    private static NearEarthObject parseNeoJsonToNeoObject(String inputJsonString) {
        JSONObject neoJsonObject = new JSONObject(inputJsonString);

        return new NearEarthObject(
                neoJsonObject.getInt("id"),
                neoJsonObject.getString("name"),
                neoJsonObject.getJSONObject("estimated_diameter").getJSONObject("meters").getFloat("estimated_diameter_min"),
                neoJsonObject.getJSONObject("estimated_diameter").getJSONObject("meters").getFloat("estimated_diameter_max"),
                neoJsonObject.getBoolean("is_potentially_hazardous_asteroid"),
                neoJsonObject.getJSONArray("close_approach_data").getJSONObject(0).getString("close_approach_date_full"),
                neoJsonObject.getJSONArray("close_approach_data").getJSONObject(0).getJSONObject("relative_velocity").getFloat("kilometers_per_second"),
                neoJsonObject.getJSONArray("close_approach_data").getJSONObject(0).getJSONObject("miss_distance").getFloat("kilometers"),
                neoJsonObject.getJSONArray("close_approach_data").getJSONObject(0).getString("orbiting_body"),
                neoJsonObject.getBoolean("is_sentry_object")
        );
    }
}
