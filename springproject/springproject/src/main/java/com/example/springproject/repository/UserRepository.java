package com.example.springproject.repository;

import com.example.springproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_register_user");
    }

    public void registerUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("p_user_name", user.getUserName());
        params.put("p_email", user.getEmail());
        params.put("p_password_hash", user.getPasswordHash());
        params.put("p_salt", user.getSalt());
        params.put("p_cover_image", user.getCoverImage());
        params.put("p_biography", user.getBiography());

        jdbcCall.execute(params);
    }
}
