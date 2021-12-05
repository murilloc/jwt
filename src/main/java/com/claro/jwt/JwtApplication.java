package com.claro.jwt;

import com.claro.jwt.domain.Role;
import com.claro.jwt.domain.User;
import com.claro.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Murillo Cesar", "murilloc", "12343", new ArrayList<Role>()));
            userService.saveUser(new User(null, "Veronica Nogueira", "veron", "12343", new ArrayList<Role>()));
            userService.saveUser(new User(null, "Maria Nogueira", "maria", "12343", new ArrayList<Role>()));
            userService.saveUser(new User(null, "Ana Paula Nogueira", "anapaula", "12343", new ArrayList<Role>()));


            userService.addRoleToUser("murilloc", "ROLE_USER");
            userService.addRoleToUser("veron", "ROLE_MANAGER");
            userService.addRoleToUser("veron", "ROLE_ADMIN");
            userService.addRoleToUser("maria", "ROLE_USER");
            userService.addRoleToUser("anapaula", "ROLE_ADMIN");
            userService.addRoleToUser("murilloc", "ROLE_SUPER_ADMIN");
        };
    }

}
