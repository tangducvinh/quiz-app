package com.quiz_app.quiz_app.configuration;

import com.quiz_app.quiz_app.entity.User;
import com.quiz_app.quiz_app.enums.Roles;
import com.quiz_app.quiz_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationinitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
//                var roles = new HashSet<String>();
//                roles.add(Roles.ADMIN.name());
                User user = User.builder().username("admin").password(passwordEncoder.encode("admin123")).build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin123");
            }
        };
    }

}
