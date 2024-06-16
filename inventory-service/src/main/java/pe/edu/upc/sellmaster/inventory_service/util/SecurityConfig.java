package pe.edu.upc.sellmaster.inventory_service.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/inventory-service/v3/api-docs/swagger-config",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/inventory/**", // Permitir acceso a todas las rutas de inventario
                                "/api/**",
                                "/swagger-resources/**",
                                "/inventory-service/v3/api-docs",
                                "/webjars/**").permitAll() // Permitir acceso a Swagger
                        .anyRequest().authenticated() // Requerir autenticación para cualquier otra ruta
                )
                .httpBasic(withDefaults()); // Configura autenticación básica
*/
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Permitir acceso a todas las rutas sin autenticación
                );

        return http.build();
    }
}
