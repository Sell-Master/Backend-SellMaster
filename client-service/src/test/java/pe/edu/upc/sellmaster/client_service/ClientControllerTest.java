package pe.edu.upc.sellmaster.client_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.client_service.controller.ClientController;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientRequest;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientResponse;
import pe.edu.upc.sellmaster.client_service.service.ClientService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test for adding a client
    @Test
    public void testAddClient() throws Exception {
        ClientRequest clientRequest = new ClientRequest("John", "Doe", 12345678, 30);
        doNothing().when(clientService).addClient(clientRequest);

        ResponseEntity<?> response = clientController.addClient(clientRequest);

        verify(clientService).addClient(clientRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Test for getting all clients
    @Test
    public void testGetAllClients() {
        List<ClientResponse> expectedResponses = List.of(
                new ClientResponse(1L, "John", "Doe", 12345678, 30)
        );
        when(clientService.getAllClients()).thenReturn(expectedResponses);

        List<ClientResponse> actualResponses = clientController.getAllClients();

        assertEquals(1, actualResponses.size());
        assertEquals(12345678, actualResponses.get(0).getDni());
    }

    // Test for getting a client by ID
    @Test
    public void testGetClientById() {
        long id = 1L;
        ClientResponse expectedResponse = new ClientResponse(id, "John", "Doe", 12345678, 30);
        when(clientService.getClientById(id)).thenReturn(expectedResponse);

        ClientResponse actualResponse = clientController.getClientById(id);

        assertEquals(id, actualResponse.getClientId());
    }

    // Test for updating a client
    @Test
    public void testUpdateClient() throws Exception {
        long id = 1L;
        ClientRequest clientRequest = new ClientRequest("Jane", "Doe", 87654321, 32);
        doNothing().when(clientService).updateClient(id, clientRequest);

        ResponseEntity<?> response = clientController.updateClient(id, clientRequest);

        verify(clientService).updateClient(id, clientRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Test for deleting a client
    @Test
    public void testDeleteClient() {
        long id = 1L;
        doNothing().when(clientService).deleteClient(id);

        clientController.deleteClient(id);

        verify(clientService).deleteClient(id);
    }

    // Test for finding clients by DNI
    @Test
    public void testFindByDni() {
        String dni = "12345678";
        List<ClientResponse> expectedResponses = List.of(
                new ClientResponse(1L, "John", "Doe", Integer.parseInt(dni), 30)
        );
        when(clientService.findByDni(dni)).thenReturn(expectedResponses);

        List<ClientResponse> actualResponses = clientController.findByDni(dni);

        assertEquals(1, actualResponses.size());
        assertEquals(dni, Integer.toString(actualResponses.get(0).getDni()));
    }

    // Test for finding clients by age
    @Test
    public void testFindByAge() {
        int age = 30;
        List<ClientResponse> expectedResponses = List.of(
                new ClientResponse(1L, "John", "Doe", 12345678, age)
        );
        when(clientService.findByAge(age)).thenReturn(expectedResponses);

        List<ClientResponse> actualResponses = clientController.findByAge(age);

        assertEquals(1, actualResponses.size());
        assertEquals(age, actualResponses.get(0).getAge());
    }
}

