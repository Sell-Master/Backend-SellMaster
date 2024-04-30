package pe.edu.upc.sellmaster.partner_service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerRequest;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerResponse;
import pe.edu.upc.sellmaster.partner_service.model.entities.Partner;
import pe.edu.upc.sellmaster.partner_service.repository.PartnerRepository;
import pe.edu.upc.sellmaster.partner_service.service.PartnerService;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PartnerServiceTest {

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private PartnerService partnerService;

    // Test para agregar un partner
    @Test
    public void testAddPartner() {
        PartnerRequest request = new PartnerRequest("ABC Corp", 12345678901L, "Some info");
        Partner partner = Partner.builder()
                .partnerName(request.getPartnerName())
                .RUC(request.getRUC())
                .additionalInfo(request.getAdditionalInfo())
                .build();
        partnerService.addPartner(request);
        verify(partnerRepository).save(any(Partner.class));
    }

    // Test para obtener todos los partners
    @Test
    public void testGetAllPartners() {
        when(partnerRepository.findAll()).thenReturn(List.of(new Partner(1L, "XYZ Corp", 98765432101L, "More info")));
        List<PartnerResponse> responses = partnerService.getAllPartners();
        assertEquals(1, responses.size(), "Expected size of partner list is incorrect");
        assertEquals("XYZ Corp", responses.get(0).getPartnerName(), "Partner name does not match expected value");
    }

    // Test para obtener un partner por ID
    @Test
    public void testGetPartnerById() {
        Partner partner = new Partner(1L, "ABC Corp", 12345678901L, "Some info");
        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));
        PartnerResponse response = partnerService.getPartnerById(1L);
        assertEquals(1L, response.getPartnerID(), "Expected partner ID to match");
    }

    // Test para actualizar un partner
    @Test
    public void testUpdatePartner() {
        Partner existingPartner = new Partner(1L, "ABC Corp", 12345678901L, "Some info");
        PartnerRequest request = new PartnerRequest("ABC Corp Updated", 12345678901L, "Updated info");
        when(partnerRepository.findById(1L)).thenReturn(Optional.of(existingPartner));
        partnerService.updatePartner(1L, request);
        assertEquals("ABC Corp Updated", existingPartner.getPartnerName(), "Partner name was not updated correctly");
        verify(partnerRepository).save(existingPartner);
    }

    // Test para encontrar un partner por RUC
    @Test
    public void testFindByRUC() {
        Partner partner = new Partner(1L, "XYZ Corp", 98765432101L, "More info");
        when(partnerRepository.findByRUC(98765432101L)).thenReturn(partner);
        PartnerResponse response = partnerService.findByRUC(98765432101L);
        assertEquals(98765432101L, response.getRUC(), "RUC does not match expected value");
    }
}