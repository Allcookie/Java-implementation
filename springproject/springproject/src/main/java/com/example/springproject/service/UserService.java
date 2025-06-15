package com.example.springproject.service;

import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import com.example.springproject.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    // ======註冊 & 密碼加鹽 & 雜湊======
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String userName, String email, String password, String coverImage, String biography,
            String phoneNumber) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setSalt(salt);
        user.setPasswordHash(hashedPassword);
        user.setCoverImage(coverImage);
        user.setBiography(biography);
        user.setPhoneNumber(phoneNumber);

        userRepository.registerUser(user);
    }

    private String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest((salt + password).getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // ======登入 & Jwt驗證======
    @Autowired
    private JwtUtil jwtUtil;

    public String login(String phoneNumber, String password) {
        System.out.println("進入 login : " + phoneNumber);

        User user = userRepository.findUserByPhone(phoneNumber);
        if (user == null) {
            throw new RuntimeException("使用者不存在");
        }

        String hashedInput = hashPassword(password, user.getSalt());
        if (!hashedInput.equals(user.getPasswordHash())) {
            throw new RuntimeException("密碼錯誤");
        }

        // 驗證通過，回傳 JWT
        return jwtUtil.generateToken(phoneNumber);
    }
}
