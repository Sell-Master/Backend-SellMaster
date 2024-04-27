package pe.edu.upc.sellmaster.client_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientRequest;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientResponse;
import pe.edu.upc.sellmaster.client_service.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // This annotation may be redundant with ResponseEntity being used
    @Operation(summary = "Add a new client", description = "Adds a new client to the system")
    public ResponseEntity<?> addClient(@RequestBody ClientRequest clientRequest) {
        try {
            clientService.addClient(clientRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all clients", description = "Retrieves a list of all clients")
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a client by ID", description = "Retrieves a specific client by their ID")
    public ClientResponse getClientById(@PathVariable("id") long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a client", description = "Updates a specific client by their ID")
    public ResponseEntity<?> updateClient(@PathVariable("id") long id, @RequestBody ClientRequest clientRequest) {
        try {
            clientService.updateClient(id, clientRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a client", description = "Deletes a specific client by their ID")
    public void deleteClient(@PathVariable("id") long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/search/by-dni")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find clients by DNI", description = "Finds clients by their DNI")
    public List<ClientResponse> findByDni(@RequestParam String dni) {
        return clientService.findByDni(dni);
    }

    @GetMapping("/search/by-age")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find clients by age", description = "Finds clients by their age")
    public List<ClientResponse> findByAge(@RequestParam int age) {
        return clientService.findByAge(age);
    }
}
