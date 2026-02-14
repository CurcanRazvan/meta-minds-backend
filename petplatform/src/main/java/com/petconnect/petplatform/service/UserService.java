package com.petconnect.petplatform.service;

import com.petconnect.petplatform.model.User;
import com.petconnect.petplatform.repository.UserRepository;
import com.petconnect.petplatform.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Injectăm adresa de bază din application.properties
    @Value("${app.base-url}")
    private String baseUrl;

    public void saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void generateResetToken(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            String token = jwtUtil.generatePasswordResetToken(user);
            System.out.println("Reset link: " + baseUrl + "/auth/reset-password?token=" + token);
        });
    }

    public void resetPasswordWithToken(String token, String newPassword) {
        String email = jwtUtil.extractUsername(token);
        var claims = jwtUtil.extractAllClaims(token);
        if (!"password_reset".equals(claims.get("purpose"))) {
            throw new IllegalArgumentException("Invalid token purpose");
        }
        if (jwtUtil.isTokenExpired(token)) {
            throw new IllegalArgumentException("Token expired");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid token"));

        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
