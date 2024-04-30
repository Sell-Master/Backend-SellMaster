package pe.edu.upc.sellmaster.dispatch_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.dispatch_service.controller.DispatchController;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.*;
import pe.edu.upc.sellmaster.dispatch_service.service.DispatchService;

import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DispatchControllerTest {

    @Mock
    private DispatchService dispatchService;

    @InjectMocks
    private DispatchController dispatchController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test for adding a dispatch
    @Test
    public void testAddDispatch() {
        DispatchRequest dispatchRequest = new DispatchRequest(1L, 2L, 3L, 10, new Date());
        DispatchResponse expectedResponse = new DispatchResponse(1L, 1L, 2L, 3L, 10, new Date());
        when(dispatchService.addDispatch(dispatchRequest)).thenReturn(expectedResponse);

        DispatchResponse actualResponse = dispatchController.addDispatch(dispatchRequest);

        verify(dispatchService).addDispatch(dispatchRequest);
        assertEquals(expectedResponse.getDispatchID(), actualResponse.getDispatchID());
    }

    // Test for getting all dispatches
    @Test
    public void testGetAllDispatches() {
        List<DispatchDetailResponse> expectedResponses = List.of(
                new DispatchDetailResponse(1L, new PartnerResponse(), new ProductResponse(), new UserResponse(), 10, new Date())
        );
        when(dispatchService.getAllDispatches()).thenReturn(expectedResponses);

        List<DispatchDetailResponse> actualResponses = dispatchController.getAllDispatches();

        assertEquals(1, actualResponses.size());
        assertEquals(10, actualResponses.get(0).getQuantity());
    }

    // Test for getting a dispatch by ID
    @Test
    public void testGetDispatchById() {
        long id = 1L;
        DispatchResponse expectedResponse = new DispatchResponse(id, 1L, 2L, 3L, 10, new Date());
        when(dispatchService.getDispatchById(id)).thenReturn(expectedResponse);

        DispatchResponse actualResponse = dispatchController.getDispatchById(id);

        assertEquals(id, actualResponse.getDispatchID());
    }

    // Test for updating a dispatch
    @Test
    public void testUpdateDispatch() {
        long id = 1L;
        DispatchRequest dispatchRequest = new DispatchRequest(1L, 2L, 3L, 20, new Date());
        DispatchResponse expectedResponse = new DispatchResponse(id, 1L, 2L, 3L, 20, new Date());
        when(dispatchService.updateDispatch(id, dispatchRequest)).thenReturn(expectedResponse);

        DispatchResponse actualResponse = dispatchController.updateDispatch(id, dispatchRequest);

        assertEquals(20, actualResponse.getQuantity());
        verify(dispatchService).updateDispatch(id, dispatchRequest);
    }

    // Test for deleting a dispatch
    @Test
    public void testDeleteDispatch() {
        long id = 1L;
        doNothing().when(dispatchService).deleteDispatch(id);

        dispatchController.deleteDispatch(id);

        verify(dispatchService).deleteDispatch(id);
    }
}

