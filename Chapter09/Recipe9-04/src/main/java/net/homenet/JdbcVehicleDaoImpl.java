package net.homenet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        jdbcTemplate.update("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
            , vehicle.getColor()
            , vehicle.getWheel()
            , vehicle.getSeat()
            , vehicle.getVehicleNo());
    }

    @Override
    public void insert(Collection<Vehicle> vehicles) {
        jdbcTemplate.batchUpdate("INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)"
            , vehicles
            , vehicles.size()
            , this::prepareStatement);
    }

    @Override
    public void update(Vehicle vehicle) {
        jdbcTemplate.update("UPDATE vehicle SET color = ?, wheel = ?, seat = ? WHERE vehicle_no = ?"
            , vehicle.getColor()
            , vehicle.getWheel()
            , vehicle.getSeat()
            , vehicle.getVehicleNo());
    }

    @Override
    public void delete(Vehicle vehicle) {
        jdbcTemplate.update("DELETE FROM vehicle WHERE vehicle_no = ?"
            , vehicle.getVehicleNo());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM vehicle");
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return jdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = ?"
            , BeanPropertyRowMapper.newInstance(Vehicle.class)
            , vehicleNo);
    }

    @Override
    public List<Vehicle> findAll() {
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
}
