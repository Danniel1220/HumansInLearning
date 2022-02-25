package com.crystal.beam;

import com.crystal.dao.Vehicle;
import com.crystal.db.DatabaseConnector;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Test;

public class ParDoAndDoFnTest {

    @Test
    public void should_output_and_clear_VINs_from_PCollection() {
        final String query = "select * from vehicles;";
        final int workers = 1;

        PCollection<Vehicle> vehiclePCollection = DatabaseConnector.selectAndGetPCollectionOfVehicles(query, "Fixed time windows", workers);
        Pipeline pipeline = vehiclePCollection.getPipeline();

        vehiclePCollection.apply(ParDo.of(new OutputVehiclesDoFn()));

        PCollection<Vehicle> processedVehiclePCollection = vehiclePCollection.apply(ParDo.of(new ClearVINsDoFn()));

        processedVehiclePCollection.apply(ParDo.of(new OutputVehiclesDoFn()));

        pipeline.run().waitUntilFinish();
    }

    static class OutputVehiclesDoFn extends DoFn<Vehicle, Vehicle> {
        @ProcessElement
        public void processElement(@Element Vehicle vehicle, OutputReceiver<Vehicle> out) {
            System.out.println(vehicle);
        }
    }

    static class ClearVINsDoFn extends DoFn<Vehicle, Vehicle> {
        @ProcessElement
        public void processElement(@Element Vehicle vehicle, OutputReceiver<Vehicle> out) {
            out.output(new Vehicle(
                    "0000000000000000",
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getYearProduced()
            ));
        }
    }
}
