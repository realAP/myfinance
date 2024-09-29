package at.devp.myfinance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Inject the username and password from application.properties
    @Value("${app.security.user.username}")
    private String username;

    @Value("${app.security.user.password}")
    private String password;

    // Define a SecurityFilterChain to handle basic security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()  // Protect all endpoints
                )
                .httpBasic();  // Enable Basic Authentication
        return http.build();
    }

    // Create an in-memory user with the username and password from application.properties
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder().encode(password))  // Use the injected username and password
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // NoOpPasswordEncoder for simplicity, no encoding of password (use BCrypt in production)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // No password encoding for now
    }
}
