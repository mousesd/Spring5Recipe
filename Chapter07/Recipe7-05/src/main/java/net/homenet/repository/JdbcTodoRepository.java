package net.homenet.repository;

import net.homenet.domain.Todo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlDialectInspection" })
@Repository
public class JdbcTodoRepository implements TodoRepository {
    private final JdbcTemplate template;

    public JdbcTodoRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Todo> findAll() {
        return template
            .query("SELECT * FROM TODO ORDER BY ID", BeanPropertyRowMapper.newInstance(Todo.class));
    }

    @Override
    public Todo findOne(long id) {
        return template
            .queryForObject("SELECT * FROM TODO WHERE ID=?", BeanPropertyRowMapper.newInstance(Todo.class), id);
    }

    @Override
    public void remove(long id) {
        template.update("DELETE FROM TODO WHERE ID=?", id);
    }

    @Override
    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            final String sql = "INSERT INTO TODO (OWNER, DESCRIPTION, COMPLETED) VALUES (?, ?, ?)";
            final GeneratedKeyHolder holder = new GeneratedKeyHolder();
            template.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{ "id" });
                ps.setString(1, todo.getOwner());
                ps.setString(2, todo.getDescription());
                ps.setBoolean(3, todo.isCompleted());
                return ps;
            }, holder);
            todo.setId(Objects.requireNonNull(holder.getKey()).longValue());
        } else {
            final String sql = "UPDATE TODO SET OWNER=?, DESCRIPTION=?, COMPLETED=?";
            template.update(sql, todo.getOwner(), todo.getDescription(), todo.isCompleted());
        }
        return null;
    }

    @Override
    public List<Todo> findByOwner(String owner) {
        return template
            .query("SELECT * FROM TODO WHERE OWNER=? ORDER BY ID"
                , BeanPropertyRowMapper.newInstance(Todo.class)
                , owner);
    }
}
