package com.example.task.configuration;

//import com.example.task.repository.UserDao;
import com.example.task.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.task.configuration.Configuration.Roles.*;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAthFilter jwtAuthFilter;
    private final UserDao userDao;
  //na razie userzy na sztywno

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/authenticate",    "/",
                        "/v2/api-docs",           // swagger
                        "/webjars/**",            // swagger-ui webjars
                        "/swagger-resources/**",  // swagger-ui resources
                        "/configuration/**",      // swagger configuration
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).
        csrf().disable();
        return http.build();
    }
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //tutaj wskazujemy serwis user detailsowy, który może mieć inną logikę pozyskiwania userów
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//        @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails timmy = User.builder()
//                .username("timmy")
//                .password(passwordEncoder().encode("password"))
//                .roles(INTERN.name())
//                .build();
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password(passwordEncoder().encode("password"))
//                .roles(SUPERVISOR.name())
//                .build();
//
//        UserDetails sarah = User.builder()
//                .username("sarah")
//                .password(passwordEncoder().encode("password"))
//                .roles(ADMIN.name())
//                .build();
//
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(timmy, john, sarah);
//
//        return userDetailsManager;
//
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(15);

    }
   //nadpisując tą wbudowaną metodę, można zaciągać dane z bazy danych
@Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userDao.findUserByEmail(email);
            }
        };
}
}
