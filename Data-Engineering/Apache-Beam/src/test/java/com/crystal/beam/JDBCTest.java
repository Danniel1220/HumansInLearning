package com.crystal.beam;

import com.crystal.beam.utils.BeamUtils;
import com.crystal.dao.Vehicle;
import com.crystal.mapper.VehicleRowMapper;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.*;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
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
        String query = "select * from apache.vehicles where id = 30;";

        PCollection<Vehicle> vehiclePCollection = pipeline.apply(JdbcIO.<Vehicle>read()
                .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(
                                "com.mysql.jdbc.Driver", URL)
                        .withUsername(USER)
                        .withPassword(PASSWORD))
                .withQuery(query)
                .withCoder(SerializableCoder.of(Vehicle.class))
                .withRowMapper(new VehicleRowMapper()));

        PAssert.that(vehiclePCollection).containsInAnyOrder(new Vehicle("WAUBH24B8XN021102", "Audi","A6", 1999));

//        // Way to do more complex asserts on each entry in the PColection
//        PAssert.that(vehiclePCollection).satisfies(new SerializableFunction<Iterable<Vehicle>, Void>() {
//            @Override
//            public Void apply(Iterable<Vehicle> input) {
//                Iterator<Vehicle> iter = input.iterator();
//                Vehicle veh = iter.next();
//                Assert.assertEquals(veh, new Vehicle("aWAUBH24B8XN021102", "Audi","A6", 1999));
//                Assert.assertFalse(iter.hasNext());
//                return null;
//            }
//        });

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
