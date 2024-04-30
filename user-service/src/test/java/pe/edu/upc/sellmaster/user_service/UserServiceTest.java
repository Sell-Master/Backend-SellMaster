package pe.edu.upc.sellmaster.user_service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.edu.upc.sellmaster.user_service.model.dtos.UserRequest;
import pe.edu.upc.sellmaster.user_service.model.dtos.UserResponse;
import pe.edu.upc.sellmaster.user_service.model.entities.TypeOfUser;
import pe.edu.upc.sellmaster.user_service.model.entities.User;
import pe.edu.upc.sellmaster.user_service.repository.TypeOfUserRepository;
import pe.edu.upc.sellmaster.user_service.repository.UserRepository;
import pe.edu.upc.sellmaster.user_service.service.UserService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TypeOfUserRepository typeOfUserRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Asegúrate de que los mocks están inicializados
        MockitoAnnotations.initMocks(this);
    }

    // Test para agregar un usuario
    @Test
    public void testAddUser() {
        UserRequest request = new UserRequest("John", "Doe", "john.doe@example.com", "password123", 1);
        TypeOfUser userType = new TypeOfUser(1, "Admin");
        when(typeOfUserRepository.findById(1L)).thenReturn(Optional.of(userType));
        userService.addUser(request);
        verify(userRepository).save(any(User.class));
    }

    // Test para obtener todos los usuarios
    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(1, "John", "Doe", "john.doe@example.com", "password123", new TypeOfUser(1, "Admin"))));
        List<UserResponse> responses = userService.getAllUsers();
        assertEquals("Expected one user in the list", 1, responses.size());
        assertEquals("Expected user first name to match", "John", responses.get(0).getFirstName());
    }

    // Test para actualizar un usuario
    @Test
    public void testUpdateUser() {
        User existingUser = new User(1, "John", "Doe", "john@example.com", "password123", new TypeOfUser(1, "Admin"));
        UserRequest request = new UserRequest("Jane", "Doe", "jane.doe@example.com", "newpassword", 1);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(typeOfUserRepository.findById(1L)).thenReturn(Optional.of(new TypeOfUser(1, "Admin")));
        userService.updateUser(1L, request);
        assertEquals("Expected first name to be updated", "Jane", existingUser.getFirstName());
        verify(userRepository).save(existingUser);
    }

    // Test para eliminar un usuario
    @Test
    public void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    // Test para obtener un usuario por ID
    @Test
    public void testGetUserById() {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", new TypeOfUser(1, "Admin"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponse response = userService.getUserById(1L);
        assertEquals("Expected user ID to match", 1, response.getUserId());
        assertEquals("Expected email to match", "john.doe@example.com", response.getEmail());
    }
}
