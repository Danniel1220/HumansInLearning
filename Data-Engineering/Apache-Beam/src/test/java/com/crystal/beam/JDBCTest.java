package com.crystal.beam;

import com.crystal.beam.utils.BeamUtils;
import com.crystal.dao.Vehicle;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.BigEndianIntegerCoder;
import org.apache.beam.sdk.coders.CannotProvideCoderException;
import org.apache.beam.sdk.coders.KvCoder;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JDBCTest {
    private static final String URL = "jdbc:mysql://localhost:3306/apache";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";


    @BeforeClass
    public static void init() {

    }

    @AfterClass
    public static void exit() {

    }

    @Test
    public void should_create_pCollection_via_pipeline_apply() {
        Pipeline pipeline = BeamUtils.createPipeline("Vehicles Job via Pipeline");
        String query = "select * from vehicles";

        pipeline.apply(JdbcIO.<KV<Integer, String>>read()
                .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(
                                "com.mysql.jdbc.Driver", URL)
                        .withUsername(USER)
                        .withPassword(PASSWORD))
                .withQuery(query)
                .withCoder(KvCoder.of(BigEndianIntegerCoder.of(), StringUtf8Coder.of()))
                .withRowMapper((JdbcIO.RowMapper<KV<Integer, String>>) resultSet ->
                        KV.of(resultSet.getInt(1), resultSet.getString(2)))
        );

        pipeline.run().waitUntilFinish();
    }


    @Test
    public void should_create_pCollection_via_resultSet() throws SQLException {
        Pipeline pipeline = BeamUtils.createPipeline("Vehicles Job via ResultSet");
        String query = "select * from vehicles where id = 30";

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Vehicle> vehicles = getVehicleListFromResultSet(resultSet);

        PCollection<Vehicle> vehiclePCollection = pipeline.apply("Populating", Create.of(vehicles));

        //PTransform printingPTransform = MapElements.into(TypeDescriptor.of(Vehicle.class)).via() -> {

        PAssert.that(vehiclePCollection).containsInAnyOrder(new Vehicle("WAUBH24B8XN021102", "Audi","A6", 1999));

        pipeline.run().waitUntilFinish();
    }

    @Test
    public void should_connect_with_jdbc() {
        String query = "select * from vehicles";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Printing database's title row
            while (resultSet.next()) {
                System.out.println(resultSet.getString("VIN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private List<Vehicle> getVehicleListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();

        while (resultSet.next()) {
            vehicles.add(new Vehicle(
                    resultSet.getString("VIN"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("yearProduced")
            ));
        }

        return vehicles;
    }
}
