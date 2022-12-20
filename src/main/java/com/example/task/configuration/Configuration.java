package com.example.task.configuration;

import com.example.task.clients.model.City;
import com.example.task.repository.AppRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.task.configuration.Configuration.Roles.*;

@org.springframework.context.annotation.Configuration
//@EnableWebMvc
public class Configuration {
    public enum Roles {

        INTERN,

        SUPERVISOR,

        ADMIN;


    }
    private  List<City> cities = new ArrayList<>();
    private AppRepository repository;
    private CitiesMiner miner;

    Configuration(CitiesMiner citiesMiner, AppRepository repository){
        this.miner = citiesMiner;
        this.repository = repository;
    }
//    @Bean
//    public void populateCities() throws ParserConfigurationException, IOException, SAXException {
//        cities = CitiesMiner.mineData();
//        for(City city: cities){
//            repository.save(city);
//        }
//    }



//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//            }



//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////                    http
////                    .httpBasic()
////                    .and()
////                    .authorizeRequests()
////                    .antMatchers("/index.html", "/", "/home", "/login").permitAll()
////                    .anyRequest().authenticated();
////            http.cors();
////        }
//////        @Bean
////        protected UserDetailsService userDetailsService() {
////            UserDetails timmy = User.builder()
////                    .username("timmy")
////                    .password(passwordEncoder().encode("password"))
////                    .roles(INTERN.name())
////                    .build();
////
////            UserDetails john = User.builder()
////                    .username("john")
////                    .password(passwordEncoder().encode("password"))
////                    .roles(SUPERVISOR.name())
////                    .build();
////
////            UserDetails sarah = User.builder()
////                    .username("sarah")
////                    .password(passwordEncoder().encode("password"))
////                    .roles(ADMIN.name())
////                    .build();
////
////            InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(timmy, john, sarah);
////
////            return userDetailsManager;
////
////        }
////        @Bean
////        public PasswordEncoder passwordEncoder(){
////
////            return new BCryptPasswordEncoder(15);
////
////        }
//
//    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
