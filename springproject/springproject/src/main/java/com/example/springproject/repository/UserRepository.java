package com.example.springproject.repository;

import com.example.springproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class UserRepository {

    private final SimpleJdbcCall registerUserCall;
    private final SimpleJdbcCall findUserByEmailCall;

    // 註冊
    @Autowired
    public UserRepository(DataSource dataSource) {
        this.registerUserCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_register_user");
        this.findUserByEmailCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_find_user_by_email")
                .returningResultSet("user", (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setSalt(rs.getString("salt"));
                    user.setCoverImage(rs.getString("cover_image"));
                    user.setBiography(rs.getString("biography"));
                    return user;
                });
    }

    public void registerUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("p_user_name", user.getUserName());
        params.put("p_email", user.getEmail());
        params.put("p_password_hash", user.getPasswordHash());
        params.put("p_salt", user.getSalt());
        params.put("p_cover_image", user.getCoverImage());
        params.put("p_biography", user.getBiography());

        registerUserCall.execute(params);
    }

    // 尋找Email 用於登入
    public User findUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("p_email", email);
        Map<String, Object> result = findUserByEmailCall.execute(params);

        // @SuppressWarnings("unchecked")
        var users = (List<User>) result.get("user");

        return users.isEmpty() ? null : users.get(0);
    }
}
