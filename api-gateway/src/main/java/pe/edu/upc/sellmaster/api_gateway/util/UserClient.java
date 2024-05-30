package pe.edu.upc.sellmaster.api_gateway.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/api/users/email/{email}")
    UserDetails loadUserByEmail(@PathVariable("email") String email);
}



