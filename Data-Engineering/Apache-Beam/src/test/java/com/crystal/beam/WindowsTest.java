package com.crystal.beam;

import com.crystal.dao.Vehicle;
import com.crystal.db.DatabaseConnector;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;

import java.awt.*;

public class WindowsTest {

    public void should_make_use_of_fixed_time_windows() {
        final String query = "select * from vehicles;";
        final int workers = 1;

        PCollection<Vehicle> vehiclePCollection = DatabaseConnector.selectAndGetPCollectionOfVehicles(query, "Fixed time windows", workers);
        Pipeline pipeline = vehiclePCollection.getPipeline();

//        PCollection<Vehicle> windowedVehicles = vehiclePCollection.apply(
//                Window.<Vehicle>into(FixedWindows.of(Duration.standardSeconds(1)))
//        );

        pipeline.run().waitUntilFinish();
    }
}
