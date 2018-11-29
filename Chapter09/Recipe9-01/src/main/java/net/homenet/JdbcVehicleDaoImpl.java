package net.homenet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class JdbcVehicleDaoImpl implements VehicleDao {
    private final DataSource dataSource;

    public JdbcVehicleDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Vehicle vehicle) {
        //# 1.JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection
        //        .prepareStatement("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)")
        //) {
        //    prepareStatement(preparedStatement, vehicle);
        //    preparedStatement.executeUpdate();
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# 2.PreparedStatementCreator(private inner class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.update(new InsertVehicleStatementCreator(vehicle));

        //# 3.PreparedStatementCreator(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.update(new PreparedStatementCreator() {
        //    @Override
        //    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        //        PreparedStatement preparedStatement = con
        //            .prepareStatement("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)");
        //        prepareStatement(preparedStatement, vehicle);
        //        return preparedStatement;
        //    }
        //});

        //# 4.PreparedStatementCreator(lambda expression)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.update(con -> {
        //    PreparedStatement preparedStatement = con
        //        .prepareStatement("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)");
        //    prepareStatement(preparedStatement, vehicle);
        //    return preparedStatement;
        //});

        //# 5.PreparedStatementSetter(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.update("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
        //    , new PreparedStatementSetter() {
        //        @Override
        //        public void setValues(PreparedStatement ps) throws SQLException {
        //            prepareStatement(ps, vehicle);
        //        }
        //    });

        //# 6.PreparedStatementSetter(lambda expression)
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
            , ps -> prepareStatement(ps, vehicle));
    }

    @Override
    public void insert(Iterable<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            insert(vehicle);
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE vehicle SET color = ?, wheel = ?, seat = ? WHERE vehicle_no = ?")
        ) {
            prepareStatement(preparedStatement, vehicle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM vehicle WHERE vehicle_no = ?")
        ) {
            preparedStatement.setString(1, vehicle.getVehicleNo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAll() {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicle")
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM vehicle WHERE vehicle_no = ?")
        ) {
            preparedStatement.setString(1, vehicleNo);

            Vehicle vehicle = null;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    vehicle = toVehicle(resultSet);
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Vehicle> findAll() {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle")
        ) {
            List<Vehicle> vehicles = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    vehicles.add(toVehicle(resultSet));
                }
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private void prepareStatement(PreparedStatement preparedStatement, Vehicle vehicle) throws SQLException {
        preparedStatement.setString(1, vehicle.getColor());
        preparedStatement.setInt(2, vehicle.getWheel());
        preparedStatement.setInt(3, vehicle.getSeat());
        preparedStatement.setString(4, vehicle.getVehicleNo());
    }

    private Vehicle toVehicle(ResultSet resultSet) throws SQLException {
        return new Vehicle(resultSet.getString("vehicle_no")
            , resultSet.getString("color")
            , resultSet.getInt("wheel")
            , resultSet.getInt("seat"));
    }

    //private class InsertVehicleStatementCreator implements PreparedStatementCreator {
    //    private final Vehicle vehicle;
    //
    //    private InsertVehicleStatementCreator(Vehicle vehicle) {
    //        this.vehicle = vehicle;
    //    }
    //
    //    @Override
    //    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    //        PreparedStatement preparedStatement = con
    //            .prepareStatement("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)");
    //        prepareStatement(preparedStatement, vehicle);
    //        return preparedStatement;
    //    }
    //}
}
