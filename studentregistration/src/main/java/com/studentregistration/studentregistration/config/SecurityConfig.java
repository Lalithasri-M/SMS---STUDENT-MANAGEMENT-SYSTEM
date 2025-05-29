package com.studentregistration.studentregistration.config;

import com.studentregistration.studentregistration.customservices.CustomUserDetailsService;
import com.studentregistration.studentregistration.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.authorizeHttpRequests(authz ->
              authz
                      .requestMatchers("/api/student/getStudentById/**").permitAll()
                      .requestMatchers("/api/student/getAllStudents").permitAll()
                      .requestMatchers("/api/course/getCourseById/**").permitAll()
                      .requestMatchers("/api/course/getAllCourses").permitAll()
                      .requestMatchers("/api/department/getDepartmentById/**").permitAll()
                      .requestMatchers("/api/department/getAllDepartments").permitAll()

                      .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "PROFESSOR", "HOD")
                      .requestMatchers("/api/course/**").hasAnyRole("ADMIN", "PROFESSOR", "HOD")
                      .requestMatchers("/api/department/**").hasAnyRole("ADMIN", "HOD")
                      .requestMatchers("/api/enrollment/**").hasRole("ADMIN")
                      .requestMatchers("/api/payment/**").hasRole("ADMIN")
                      .requestMatchers("/api/users/**").hasRole("ADMIN")

                      .anyRequest().permitAll()
              )
              .formLogin(form -> form.disable())
              .csrf(csrf -> csrf.disable())
              .sessionManagement( session -> session
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
      ;
      return httpSecurity.build();
  }

  @Bean
  public UserDetailsService userDetailService() {
      return new CustomUserDetailsService();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setUserDetailsService(userDetailService());
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
  }

    @Bean
    public AuthenticationManager authenticationManager() {

        return new ProviderManager(List.of(authenticationProvider()));
    }
}
