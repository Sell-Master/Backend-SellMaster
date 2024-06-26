package pe.edu.upc.sellmaster.api_gateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Deshabilita CSRF
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/api/**",
                                "/api/inventory/**", // Asegúrate de que esta ruta esté permitida
                                "/api/users/email/**",
                                "/api/auth/**",
                                "/v3/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/client-service/v3/api-docs",
                                "/dispatch-service/v3/api-docs",
                                "/inventory-service/v3/api-docs",
                                "/order-service/v3/api-docs",
                                "/partner-service/v3/api-docs",
                                "/product-service/v3/api-docs",
                                "/user-service/v3/api-docs"
                        ).permitAll() // Permitir acceso a Swagger y API docs
                        .anyExchange().authenticated() // Requerir autenticación para cualquier otra ruta
                ); // Añadir el filtro JWT

        return http.build();
    }
}

