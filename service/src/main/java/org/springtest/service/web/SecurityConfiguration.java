package org.springtest.service.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    UserDetailsRepository userDetailsRepository () {

        UserDetails user = User.withUsername("user").roles("USER").password("password").build();

        return new MapUserDetailsRepository(user);
    }
}
