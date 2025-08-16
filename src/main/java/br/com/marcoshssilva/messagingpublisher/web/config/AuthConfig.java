package br.com.marcoshssilva.messagingpublisher.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity()
public class AuthConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AuthConfig.class);
    private static final String[] CONTEXTS_ALLOWED_BY_ALL = new String[] { "/actuator/health/**", "/", "/index.html" };

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain chain1(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(authorized ->
                authorized
                        .requestMatchers(CONTEXTS_ALLOWED_BY_ALL).permitAll()
                        .anyRequest().authenticated());

        LOG.info("Spring Security configurado com sucesso!");
        return httpSecurity.getOrBuild();
    }

}
