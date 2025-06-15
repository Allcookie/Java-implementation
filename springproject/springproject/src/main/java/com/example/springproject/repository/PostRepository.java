package com.example.springproject.repository;

import com.example.springproject.model.Post;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.List;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {
    private final SimpleJdbcCall createPostCall;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PostRepository(DataSource dataSource) {
        this.createPostCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_create_post");
    }

    public void createPost(Post post) {
        System.out.println("準備發布文章...");
        Map<String, Object> params = new HashMap<>();
        params.put("p_user_id", post.getUserId());
        params.put("p_content", post.getContent());
        params.put("p_image", post.getImage());

        createPostCall.execute(params);
    }

    // 查詢貼文
    public List<Post> findAllPosts() {
        String sql = "SELECT * FROM posts ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapPost(rs));
    }

    private Post mapPost(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setUserId(rs.getInt("user_id"));
        post.setContent(rs.getString("content"));
        post.setImage(rs.getString("image"));
        
        // 將 Timestamp 轉換為 OffsetDateTime（預設用 UTC 為基準）
        var timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            post.setCreatedAt(timestamp.toInstant().atOffset(ZoneOffset.UTC));
        }
        
        return post;
    }
}
