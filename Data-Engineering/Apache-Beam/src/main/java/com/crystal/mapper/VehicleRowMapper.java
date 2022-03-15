package com.crystal.mapper;

import com.crystal.dao.Vehicle;
import org.apache.beam.sdk.io.jdbc.JdbcIO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements JdbcIO.RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getString("VIN"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("yearProduced"),
                resultSet.getInt("eventTime"));
    }
}
