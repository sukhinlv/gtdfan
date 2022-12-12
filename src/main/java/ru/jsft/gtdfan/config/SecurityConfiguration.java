package ru.jsft.gtdfan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.jsft.gtdfan.model.Role;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;
import ru.jsft.gtdfan.web.security.AuthorizedUser;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
            return new AuthorizedUser(optionalUser.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' not found")));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/tasks/**", "/api/v1/users/profile/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/categories/**", "/api/v1/priorities/**").authenticated()
                .antMatchers("/api/**").hasRole(Role.ADMIN.name())
                .anyRequest().denyAll()
                .and().httpBasic()
                .and().csrf().disable();
        return http.build();
    }
}
