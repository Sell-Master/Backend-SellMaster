package pe.edu.upc.sellmaster.user_service;

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
import pe.edu.upc.sellmaster.user_service.controller.TypeOfUserController;
import pe.edu.upc.sellmaster.user_service.model.dtos.TypeOfUserRequest;
import pe.edu.upc.sellmaster.user_service.model.dtos.TypeOfUserResponse;
import pe.edu.upc.sellmaster.user_service.service.TypeOfUserService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TypeOfUserControllerTest {

    @Mock
    private TypeOfUserService typeOfUserService;

    @InjectMocks
    private TypeOfUserController typeOfUserController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test para agregar un tipo de usuario
    @Test
    public void testAddTypeOfUser() {
        TypeOfUserRequest typeOfUserRequest = new TypeOfUserRequest("Admin");
        doNothing().when(typeOfUserService).addTypeOfUser(typeOfUserRequest);

        ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.CREATED).build();
        typeOfUserController.addTypeOfUser(typeOfUserRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(typeOfUserService).addTypeOfUser(typeOfUserRequest);
    }

    // Test para obtener todos los tipos de usuarios
    @Test
    public void testGetAllTypeOfUsers() {
        List<TypeOfUserResponse> expectedResponses = List.of(new TypeOfUserResponse(1L, "Admin"));
        when(typeOfUserService.getAllTypeOfUsers()).thenReturn(expectedResponses);

        ResponseEntity<List<TypeOfUserResponse>> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(typeOfUserController.getAllTypeOfUsers());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Admin", response.getBody().get(0).getDescription());
    }

    // Test para actualizar un tipo de usuario
    @Test
    public void testUpdateTypeOfUser() {
        long id = 1L;
        TypeOfUserRequest typeOfUserRequest = new TypeOfUserRequest("Moderator");
        doNothing().when(typeOfUserService).updateTypeOfUser(id, typeOfUserRequest);

        ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.OK).build();
        typeOfUserController.updateTypeOfUser(id, typeOfUserRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(typeOfUserService).updateTypeOfUser(id, typeOfUserRequest);
    }

    // Test para eliminar un tipo de usuario
    @Test
    public void testDeleteTypeOfUser() {
        long id = 1L;
        doNothing().when(typeOfUserService).deleteTypeOfUser(id);

        ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        typeOfUserController.deleteTypeOfUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(typeOfUserService).deleteTypeOfUser(id);
    }
}

