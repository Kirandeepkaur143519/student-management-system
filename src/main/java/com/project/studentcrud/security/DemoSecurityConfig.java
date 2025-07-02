package com.project.studentcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.setUsersByUsernameQuery(
//                "SELECT username, password, enabled FROM users WHERE username = ?"
//        );
//
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//                "SELECT username, authority FROM authorities WHERE username = ?"
//        );
//
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails hod = User.withDefaultPasswordEncoder()
                .username("hod1")
                .password("fun123")
                .roles("HOD")
                .build();

        UserDetails professor = User.withDefaultPasswordEncoder()
                .username("prof1")
                .password("fun123")
                .roles("PROFESSOR")
                .build();

        UserDetails student = User.withDefaultPasswordEncoder()
                .username("stud1")
                .password("fun123")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(hod, professor, student);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(config -> config

                        // Public pages
                        .requestMatchers("/login", "/access-denied").permitAll()
                        .requestMatchers("/api/**").permitAll()

                        // HOD-only access
                        .requestMatchers("/students/delete/**", "/students/deleteAll").hasRole("HOD")

                        .requestMatchers("/students/upload-form", "/students/upload-csv").hasRole("HOD")

                        // Professor + HOD access
                        .requestMatchers("/students/add", "/students/save", "/students/edit/**").hasAnyRole("PROFESSOR", "HOD")

                        // Student + Professor + HOD access
                        .requestMatchers("/students").hasAnyRole("STUDENT", "PROFESSOR", "HOD")

                        // All other requests need authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/students", true)
                        .permitAll()
                )
                .exceptionHandling(config -> config
                        .accessDeniedPage("/access-denied")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

}

