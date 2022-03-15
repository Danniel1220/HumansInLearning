package com.crystal.db;

import com.crystal.beam.utils.BeamUtils;
import com.crystal.dao.Vehicle;
import com.crystal.mapper.VehicleRowMapper;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/apache";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    public static PCollection<Vehicle> selectAndGetPCollectionOfVehicles(String query, String jobName, int workers) {
        Pipeline pipeline = BeamUtils.createPipeline(jobName, workers);

        PTransform<PBegin, PCollection<Vehicle>> readFromDatabasePTransform = JdbcIO.<Vehicle>read()
                .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(
                                "com.mysql.jdbc.Driver", URL)
                        .withUsername(USER)
                        .withPassword(PASSWORD))
                .withQuery(query)
                .withCoder(SerializableCoder.of(Vehicle.class))
                //.withCoder(SerializableCoder.of(this.inputClass))
                .withRowMapper(new VehicleRowMapper());

        // jdbc repo on spring data
        // remove mapper and apply mapper afterwards after method call

        return pipeline.apply(readFromDatabasePTransform);
    }
}
