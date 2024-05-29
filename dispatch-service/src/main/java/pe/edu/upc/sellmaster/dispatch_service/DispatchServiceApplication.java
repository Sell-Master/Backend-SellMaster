package pe.edu.upc.sellmaster.dispatch_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableDiscoveryClient
@EnableFeignClients
@EnableWebMvc
@SpringBootApplication
public class DispatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchServiceApplication.class, args);
	}

}
