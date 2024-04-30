package pe.edu.upc.sellmaster.partner_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.partner_service.controller.PartnerController;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerRequest;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerResponse;
import pe.edu.upc.sellmaster.partner_service.service.PartnerService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PartnerControllerTest {

    @Mock
    private PartnerService partnerService;

    @InjectMocks
    private PartnerController partnerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test para agregar un socio
    @Test
    public void testAddPartner() {
        PartnerRequest partnerRequest = new PartnerRequest("XYZ Corp", 123456789, "Some info");
        doNothing().when(partnerService).addPartner(partnerRequest);

        partnerController.addPartner(partnerRequest);

        verify(partnerService).addPartner(partnerRequest);
    }

    // Test para obtener todos los socios
    @Test
    public void testGetAllPartners() {
        List<PartnerResponse> expectedResponses = List.of(
                new PartnerResponse(1L, "XYZ Corp", 123456789, "Some info")
        );
        when(partnerService.getAllPartners()).thenReturn(expectedResponses);

        List<PartnerResponse> responses = partnerController.getAllPartners();

        assertEquals(1, responses.size());
        assertEquals("XYZ Corp", responses.get(0).getPartnerName());
    }

    // Test para obtener un socio por ID
    @Test
    public void testGetPartnerById() {
        long id = 1L;
        PartnerResponse expectedResponse = new PartnerResponse(id, "XYZ Corp", 123456789, "Some info");
        when(partnerService.getPartnerById(id)).thenReturn(expectedResponse);

        PartnerResponse response = partnerController.getPartnerById(id);

        assertEquals(id, response.getPartnerID());
    }

    // Test para actualizar un socio
    @Test
    public void testUpdatePartner() {
        long id = 1L;
        PartnerRequest partnerRequest = new PartnerRequest("XYZ Corp Updated", 123456789, "Updated info");
        doNothing().when(partnerService).updatePartner(id, partnerRequest);

        partnerController.updatePartner(id, partnerRequest);

        verify(partnerService).updatePartner(id, partnerRequest);
    }

    // Test para eliminar un socio
    @Test
    public void testDeletePartner() {
        long id = 1L;
        doNothing().when(partnerService).deletePartner(id);

        partnerController.deletePartner(id);

        verify(partnerService).deletePartner(id);
    }

    // Test para buscar un socio por RUC
    @Test
    public void testFindByRUC() {
        long RUC = 123456789;
        PartnerResponse expectedResponse = new PartnerResponse(1L, "XYZ Corp", RUC, "Some info");
        when(partnerService.findByRUC(RUC)).thenReturn(expectedResponse);

        PartnerResponse response = partnerController.findByRUC(RUC);

        assertEquals(RUC, response.getRUC());
    }
}

