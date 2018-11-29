package net.homenet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("Duplicates")
public class JdbcVehicleDaoImpl implements VehicleDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcVehicleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.update("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
        //    , ps -> prepareStatement(ps, vehicle));

        //# 7.Update a database with a SQL statement and parameter values
        jdbcTemplate.update("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
            , vehicle.getColor()
            , vehicle.getWheel()
            , vehicle.getSeat()
            , vehicle.getVehicleNo());
    }

    @Override
    public void insert(Collection<Vehicle> vehicles) {
        //# 1.
        //for (Vehicle vehicle : vehicles) {
        //    insert(vehicle);
        //}

        //# 2.Batch update a database(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.batchUpdate("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
        //    , vehicles
        //    , vehicles.size()
        //    , new ParameterizedPreparedStatementSetter<Vehicle>() {
        //        @Override
        //        public void setValues(PreparedStatement ps, Vehicle argument) throws SQLException {
        //            prepareStatement(ps, argument);
        //        }
        //    });

        //# 3.Batch update a database(lambda expression #1)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.batchUpdate("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
        //    , vehicles
        //    , vehicles.size()
        //    , (ps, argument) -> prepareStatement(ps, argument));

        //# 4.Batch update a database(lambda expression #2)
        jdbcTemplate.batchUpdate("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
            , vehicles
            , vehicles.size()
            , this::prepareStatement);
    }

    @Override
    public void update(Vehicle vehicle) {
        //# JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection
        //        .prepareStatement("UPDATE vehicle SET color = ?, wheel = ?, seat = ? WHERE vehicle_no = ?")
        //) {
        //    prepareStatement(preparedStatement, vehicle);
        //    preparedStatement.executeUpdate();
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# Update a database with a SQL statement and parameter values
        jdbcTemplate.update("UPDATE vehicle SET color = ?, wheel = ?, seat = ? WHERE vehicle_no = ?"
            , vehicle.getColor()
            , vehicle.getWheel()
            , vehicle.getSeat()
            , vehicle.getVehicleNo());
    }

    @Override
    public void delete(Vehicle vehicle) {
        //# JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection
        //        .prepareStatement("DELETE FROM vehicle WHERE vehicle_no = ?")
        //) {
        //    preparedStatement.setString(1, vehicle.getVehicleNo());
        //    preparedStatement.executeUpdate();
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# Update a database with a SQL statement and parameter values
        jdbcTemplate.update("DELETE FROM vehicle WHERE vehicle_no = ?"
            , vehicle.getVehicleNo());
    }

    @Override
    public void deleteAll() {
        //# JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicle")
        //) {
        //    preparedStatement.executeUpdate();
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# Update a database with a SQL statement and parameter values
        jdbcTemplate.update("DELETE FROM vehicle");
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        //# 1.JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection
        //        .prepareStatement("SELECT * FROM vehicle WHERE vehicle_no = ?")
        //) {
        //    preparedStatement.setString(1, vehicleNo);
        //
        //    Vehicle vehicle = null;
        //    try (ResultSet resultSet = preparedStatement.executeQuery()) {
        //        if (resultSet.next()) {
        //            vehicle = toVehicle(resultSet);
        //        }
        //    }
        //    return vehicle;
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# 2.RowCallbackHandler(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //Vehicle vehicle = new Vehicle();
        //jdbcTemplate.query("SELECT * FROM vehicle WHERE vehicle_no = ?", new RowCallbackHandler() {
        //    @Override
        //    public void processRow(ResultSet rs) throws SQLException {
        //        vehicle.setVehicleNo(rs.getString("vehicle_no"));
        //        vehicle.setColor(rs.getString("color"));
        //        vehicle.setWheel(rs.getInt("wheel"));
        //        vehicle.setSeat(rs.getInt("seat"));
        //    }
        //}, vehicleNo);
        //return vehicle;

        //# 3.RowCallbackHandler(lambda expression)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //Vehicle vehicle = new Vehicle();
        //jdbcTemplate.query("SELECT * FROM vehicle WHERE vehicle_no = ?", rs -> {
        //    vehicle.setVehicleNo(rs.getString("vehicle_no"));
        //    vehicle.setColor(rs.getString("color"));
        //    vehicle.setWheel(rs.getInt("wheel"));
        //    vehicle.setSeat(rs.getInt("seat"));
        //}, vehicleNo);
        //return vehicle;

        //# 4.RowMapper<T>(private inner class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = ?"
        //    , new VehicleRowMapper()
        //    , vehicleNo);

        //# 5.RowMapper<T>(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = ?", new RowMapper<Vehicle>() {
        //    @Override
        //    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        //        return toVehicle(rs);
        //    }
        //}, vehicleNo);

        //# 6.RowMapper<T>(lambda expression)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = ?"
        //    , (rs, rowNum) -> toVehicle(rs)
        //    , vehicleNo);

        //# 7.RowMapper<T>(BeanPropertyRowMapper<T>)
        return jdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = ?"
            , BeanPropertyRowMapper.newInstance(Vehicle.class)
            , vehicleNo);
    }

    @Override
    public List<Vehicle> findAll() {
        // 1.JDBC API
        //try (
        //    Connection connection = dataSource.getConnection();
        //    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle")
        //) {
        //    List<Vehicle> vehicles = new ArrayList<>();
        //    try (ResultSet resultSet = preparedStatement.executeQuery()) {
        //        if (resultSet.next()) {
        //            vehicles.add(toVehicle(resultSet));
        //        }
        //    }
        //    return vehicles;
        //} catch (SQLException e) {
        //    throw new RuntimeException();
        //}

        //# RowMapper<T>(private inner class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.query("SELECT * FROM vehicle", new VehicleRowMapper());

        //# RowMapper<T>(anonymous class)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.query("SELECT * FROM vehicle", new RowMapper<Vehicle>() {
        //    @Override
        //    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        //        return toVehicle(rs);
        //    }
        //});

        //# RowMapper<T>(lambda expression #1)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //return jdbcTemplate.query("SELECT * FROM vehicle", (rs, rowNum) -> toVehicle(rs));

        //# 2.RowMapper<T>(lambda expression #2)
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM vehicle");
        //return rows.stream().map(row -> {
        //    Vehicle vehicle = new Vehicle();
        //    vehicle.setVehicleNo((String) row.get("vehicle_no"));
        //    vehicle.setColor((String) row.get("color"));
        //    vehicle.setWheel((Integer) row.get("wheel"));
        //    vehicle.setSeat((Integer) row.get("seat"));
        //    return vehicle;
        //}).collect(Collectors.toList());

        //# 3.RowMapper<T>(BeanPropertyRowMapper<T>)
        return jdbcTemplate.query("SELECT * FROM vehicle"
            , BeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    @Override
    public String getColor(String vehicleNo) {
        return jdbcTemplate.queryForObject("SELECT color FROM vehicle WHERE vehicle_no = ?", String.class, vehicleNo);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int countAll() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM vehicle", Integer.class);
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

    private class InsertVehicleStatementCreator implements PreparedStatementCreator {
        private final Vehicle vehicle;

        private InsertVehicleStatementCreator(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement preparedStatement = con
                .prepareStatement("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)");
            prepareStatement(preparedStatement, vehicle);
            return preparedStatement;
        }
    }

    private class VehicleRowMapper implements RowMapper<Vehicle> {
        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return toVehicle(rs);
        }
    }
}
