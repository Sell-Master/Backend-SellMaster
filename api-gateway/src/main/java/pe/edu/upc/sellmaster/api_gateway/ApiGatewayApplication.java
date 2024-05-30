package pe.edu.upc.sellmaster.api_gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "pe.edu.upc.sellmaster.api_gateway.security.User")
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return factory -> factory.setPort(8080);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Rutas para user-service
				.route(r -> r.path("/user-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/login").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/register").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser/{id}").and().method(HttpMethod.PUT).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser/{id}").and().method(HttpMethod.DELETE).uri("lb://user-service"))
				.route(r -> r.path("/api/users").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/users").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.PUT).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.DELETE).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/login").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/register").and().method(HttpMethod.POST).uri("lb://user-service"))
				// Rutas para client-service
				.route(r -> r.path("/client-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://client-service"))
				.route(r -> r.path("/api/clients").and().method(HttpMethod.POST).uri("lb://client-service"))
				.route(r -> r.path("/api/clients").and().method(HttpMethod.GET).uri("lb://client-service"))
				.route(r -> r.path("/api/clients/{id}").and().method(HttpMethod.GET).uri("lb://client-service"))
				.route(r -> r.path("/api/clients/{id}").and().method(HttpMethod.PUT).uri("lb://client-service"))
				.route(r -> r.path("/api/clients/{id}").and().method(HttpMethod.DELETE).uri("lb://client-service"))
				.route(r -> r.path("/api/clients/search/by-dni").and().method(HttpMethod.GET).uri("lb://client-service"))
				.route(r -> r.path("/api/clients/search/by-age").and().method(HttpMethod.GET).uri("lb://client-service"))
				// Rutas para dispatch-service
				.route(r -> r.path("/dispatch-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://dispatch-service"))
				.route(r -> r.path("/api/dispatches").and().method(HttpMethod.POST).uri("lb://dispatch-service"))
				.route(r -> r.path("/api/dispatches").and().method(HttpMethod.GET).uri("lb://dispatch-service"))
				.route(r -> r.path("/api/dispatches/{id}").and().method(HttpMethod.GET).uri("lb://dispatch-service"))
				.route(r -> r.path("/api/dispatches/{id}").and().method(HttpMethod.PUT).uri("lb://dispatch-service"))
				.route(r -> r.path("/api/dispatches/{id}").and().method(HttpMethod.DELETE).uri("lb://dispatch-service"))
				// Rutas para inventory-service
				.route(r -> r.path("/inventory-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://inventory-service"))
				.route(r -> r.path("/api/inventory").and().method(HttpMethod.POST).uri("lb://inventory-service"))
				.route(r -> r.path("/api/inventory").and().method(HttpMethod.GET).uri("lb://inventory-service"))
				.route(r -> r.path("/api/inventory/{id}").and().method(HttpMethod.GET).uri("lb://inventory-service"))
				.route(r -> r.path("/api/inventory/{id}").and().method(HttpMethod.PUT).uri("lb://inventory-service"))
				.route(r -> r.path("/api/inventory/{id}").and().method(HttpMethod.DELETE).uri("lb://inventory-service"))
				// Rutas para order-service
				.route(r -> r.path("/order-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/api/order-details").and().method(HttpMethod.POST).uri("lb://order-service"))
				.route(r -> r.path("/api/order-details").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/api/order-details/{id}").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/api/order-details/{id}").and().method(HttpMethod.PUT).uri("lb://order-service"))
				.route(r -> r.path("/api/order-details/{id}").and().method(HttpMethod.DELETE).uri("lb://order-service"))
				.route(r -> r.path("/api/orders").and().method(HttpMethod.POST).uri("lb://order-service"))
				.route(r -> r.path("/api/orders").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/api/orders/{id}").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/api/orders/{id}").and().method(HttpMethod.PUT).uri("lb://order-service"))
				.route(r -> r.path("/api/orders/{id}").and().method(HttpMethod.DELETE).uri("lb://order-service"))
				// Rutas para partner-service
				.route(r -> r.path("/partner-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners").and().method(HttpMethod.POST).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners").and().method(HttpMethod.GET).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners/{id}").and().method(HttpMethod.GET).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners/{id}").and().method(HttpMethod.PUT).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners/{id}").and().method(HttpMethod.DELETE).uri("lb://partner-service"))
				.route(r -> r.path("/api/partners/search/by-ruc").and().method(HttpMethod.GET).uri("lb://partner-service"))
				// Rutas para product-service
				.route(r -> r.path("/product-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://product-service"))
				.route(r -> r.path("/api/products").and().method(HttpMethod.POST).uri("lb://product-service"))
				.route(r -> r.path("/api/products").and().method(HttpMethod.GET).uri("lb://product-service"))
				.route(r -> r.path("/api/products/{id}").and().method(HttpMethod.GET).uri("lb://product-service"))
				.route(r -> r.path("/api/products/{id}").and().method(HttpMethod.PUT).uri("lb://product-service"))
				.route(r -> r.path("/api/products/{id}").and().method(HttpMethod.DELETE).uri("lb://product-service"))
				.build();
	}
}
