package pe.edu.upc.sellmaster.user_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.user_service.controller.UserController;
import pe.edu.upc.sellmaster.user_service.model.dtos.UserRequest;
import pe.edu.upc.sellmaster.user_service.model.dtos.UserResponse;
import pe.edu.upc.sellmaster.user_service.service.UserService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test para agregar un usuario
    @Test
    public void testAddUser() {
        UserRequest userRequest = new UserRequest("John", "Doe", "john.doe@example.com", "password", 1L);
        doNothing().when(userService).addUser(userRequest);

        userController.addUser(userRequest);

        verify(userService).addUser(userRequest);
    }

    // Test para obtener todos los usuarios
    @Test
    public void testGetAllUsers() {
        List<UserResponse> expectedResponses = List.of(
                new UserResponse(1L, "John", "Doe", "john.doe@example.com",
                        new UserResponse.SimpleTypeOfUserResponse(1L, "Admin"))
        );
        when(userService.getAllUsers()).thenReturn(expectedResponses);

        List<UserResponse> responses = userController.getAllUsers();

        assertEquals(1, responses.size());
        assertEquals("John", responses.get(0).getFirstName());
        assertEquals("Admin", responses.get(0).getTypeOfUser().getDescription());
    }

    // Test para actualizar un usuario
    @Test
    public void testUpdateUser() {
        long id = 1L;
        UserRequest userRequest = new UserRequest("Jane", "Doe", "jane.doe@example.com", "newpassword", 1L);
        doNothing().when(userService).updateUser(id, userRequest);

        userController.updateUser(id, userRequest);

        verify(userService).updateUser(id, userRequest);
    }

    // Test para eliminar un usuario
    @Test
    public void testDeleteUser() {
        long id = 1L;
        doNothing().when(userService).deleteUser(id);

        userController.deleteUser(id);

        verify(userService).deleteUser(id);
    }

    // Test para obtener un usuario por ID
    @Test
    public void testGetUserById() {
        long id = 1L;
        UserResponse expectedResponse = new UserResponse(1L, "John", "Doe", "john@example.com",
                new UserResponse.SimpleTypeOfUserResponse(1L, "Admin"));
        when(userService.getUserById(id)).thenReturn(expectedResponse);

        UserResponse response = userController.getUserById(id);

        assertEquals("John", response.getFirstName());
        assertEquals(id, response.getUserId());
    }
}

