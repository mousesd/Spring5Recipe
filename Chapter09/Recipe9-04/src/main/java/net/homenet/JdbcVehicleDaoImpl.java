package net.homenet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

@SuppressWarnings("Duplicates")
public class JdbcVehicleDaoImpl implements VehicleDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcVehicleDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insert(Vehicle vehicle) {
        //# 1.Map<String, Object>
        //namedParameterJdbcTemplate.update(
        //    "INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (:color, :wheel, :seat, :vehicle_no)"
        //    , toParameterMap(vehicle));

        //# 2.SqlParameterSource - MapSqlParameterSource
        SqlParameterSource paramSource = new MapSqlParameterSource(toParameterMap(vehicle));
        namedParameterJdbcTemplate.update(
            "INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (:color, :wheel, :seat, :vehicle_no)"
            , paramSource);
    }

    @Override
    public void insert(Collection<Vehicle> vehicles) {
        Map<String, Object>[] batchValues = vehicles.stream()
            .map(this::toParameterMap)
            .toArray((IntFunction<Map<String, Object>[]>) Map[]::new);
        namedParameterJdbcTemplate.batchUpdate(
            "INSERT INTO vehicle (color, wheel, seat, vehicle_no) VALUES (:color, :wheel, :seat, :vehicle_no)"
            , batchValues);
    }

    @Override
    public void update(Vehicle vehicle) {
        namedParameterJdbcTemplate.update(
            "UPDATE vehicle SET color = :color, wheel = :wheel, seat = :seat WHERE vehicle_no = :vehicle_no"
            , toParameterMap(vehicle));
    }

    @Override
    public void delete(Vehicle vehicle) {
        namedParameterJdbcTemplate.update("DELETE FROM vehicle WHERE vehicle_no = :vehicle_no", toParameterMap(vehicle));
    }

    @Override
    public void deleteAll() {
        //# NamedParameterJdbcTemplate.update()에 인수를 전달하지 않는 오버로드 형태가 없음
        jdbcTemplate.update("DELETE FROM vehicle");
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("vehicle_no", vehicleNo);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM vehicle WHERE vehicle_no = :vehicle_no"
            , paramMap
            , BeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    @Override
    public List<Vehicle> findAll() {
        return namedParameterJdbcTemplate.query("SELECT * FROM vehicle"
            , BeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    @Override
    public String getColor(String vehicleNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("vehicle_no", vehicleNo);
        return namedParameterJdbcTemplate.queryForObject("SELECT color FROM vehicle WHERE vehicle_no = :vehicle_no"
            , paramMap, String.class);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int countAll() {
        //# NamedParameterJdbcTemplate.queryForObject()에서 인수를 전달하지 않는 오버로드 형태가 없음
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM vehicle", Integer.class);
    }

    private Map<String, Object> toParameterMap(Vehicle vehicle) {
        Map<String, Object> params = new HashMap<>();
        params.put("vehicle_no", vehicle.getVehicleNo());
        params.put("color", vehicle.getColor());
        params.put("wheel", vehicle.getWheel());
        params.put("seat", vehicle.getSeat());
        return params;
    }
}
