package pe.edu.upc.sellmaster.client_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientRequest;
import pe.edu.upc.sellmaster.client_service.model.dtos.ClientResponse;
import pe.edu.upc.sellmaster.client_service.model.entities.Client;
import pe.edu.upc.sellmaster.client_service.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public void addClient(ClientRequest request) throws Exception {
        Long count = clientRepository.countByDni(request.getDni());
        if (count >= 2) {
            throw new Exception("A maximum of 2 clients can share the same DNI.");
        }

        Client client = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .age(request.getAge())
                .build();
        clientRepository.save(client);
        log.info("Client added: {}", client);
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::mapToClientResponse)
                .toList();
    }

    public ClientResponse getClientById(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return mapToClientResponse(client);
    }

    @Transactional
    public void updateClient(long id, ClientRequest request) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        if (client.getDni() != request.getDni()) {
            Long count = clientRepository.countByDni(request.getDni());
            if (count >= 2) {
                throw new Exception("A maximum of 2 clients can share the same DNI.");
            }
        }

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setDni(request.getDni());
        client.setAge(request.getAge());
        clientRepository.save(client);
        log.info("Updated Client: {}", client);
    }


    public void deleteClient(long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
        log.info("Deleted Client with id: {}", id);
    }

    public List<ClientResponse> findByDni(String dni) {
        return clientRepository.findByDni(Integer.parseInt(dni)).stream()
                .map(this::mapToClientResponse)
                .toList();
    }

    public List<ClientResponse> findByAge(int age) {
        return clientRepository.findByAge(age).stream()
                .map(this::mapToClientResponse)
                .toList();
    }

    private ClientResponse mapToClientResponse(Client client) {
        return ClientResponse.builder()
                .clientId(client.getClientId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .dni(client.getDni())
                .age(client.getAge())
                .build();
    }
}

