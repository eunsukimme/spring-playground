package hello.hellospring.repository;
import hello.hellospring.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    // Autowired 생략가능!
    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }
    @Override
    public Optional<User> findById(Long id) {
        List<User> result = jdbcTemplate.query("select * from user where id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", userRowMapper());
    }
    @Override
    public Optional<User> findByName(String name) {
        List<User> result = jdbcTemplate.query("select * from user where name = ?", userRowMapper(), name);
        return result.stream().findAny();
    }
    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return user;
        };
    }
}