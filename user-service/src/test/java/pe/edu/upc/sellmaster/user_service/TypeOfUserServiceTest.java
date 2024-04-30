package pe.edu.upc.sellmaster.user_service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.edu.upc.sellmaster.user_service.model.dtos.TypeOfUserRequest;
import pe.edu.upc.sellmaster.user_service.model.dtos.TypeOfUserResponse;
import pe.edu.upc.sellmaster.user_service.model.entities.TypeOfUser;
import pe.edu.upc.sellmaster.user_service.repository.TypeOfUserRepository;
import pe.edu.upc.sellmaster.user_service.service.TypeOfUserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TypeOfUserServiceTest {

    @Mock
    private TypeOfUserRepository typeOfUserRepository;

    @InjectMocks
    private TypeOfUserService typeOfUserService;

    // Test para agregar un tipo de usuario
    @Test
    public void testAddTypeOfUser() {
        TypeOfUserRequest request = new TypeOfUserRequest("Admin");
        TypeOfUserService service = new TypeOfUserService(mock(TypeOfUserRepository.class));

        // Asegurarse de que el repositorio salva el tipo de usuario correctamente
        service.addTypeOfUser(request);
        verify(service.typeOfUserRepository).save(any(TypeOfUser.class));
    }

    // Test para obtener todos los tipos de usuario
    @Test
    public void testGetAllTypeOfUsers() {
        TypeOfUserService service = new TypeOfUserService(mock(TypeOfUserRepository.class));
        when(service.typeOfUserRepository.findAll()).thenReturn(List.of(new TypeOfUser(1, "Admin")));

        List<TypeOfUserResponse> responses = service.getAllTypeOfUsers();

        assertEquals( 1, responses.size(), "Expected size of user types is incorrect");
        assertEquals( "Admin", responses.get(0).getDescription(), "User description does not match expected value");
    }

    // Test para actualizar un tipo de usuario
    @Test
    public void testUpdateTypeOfUser() {
        TypeOfUser existingUser = new TypeOfUser(1, "Admin");
        TypeOfUserRequest request = new TypeOfUserRequest("SuperAdmin");
        TypeOfUserService service = new TypeOfUserService(mock(TypeOfUserRepository.class));
        when(service.typeOfUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        service.updateTypeOfUser(1L, request);

        // Verificaciones y mensajes de error personalizados
        assertEquals("SuperAdmin", existingUser.getDescription(), "User description was not updated correctly");
        verify(service.typeOfUserRepository).save(existingUser);
    }

    // Test para eliminar un tipo de usuario
    @Test
    public void testDeleteTypeOfUser() {
        TypeOfUserService service = new TypeOfUserService(mock(TypeOfUserRepository.class));
        doNothing().when(service.typeOfUserRepository).deleteById(1L);

        service.deleteTypeOfUser(1L);

        // Verifica que el método de eliminar fue llamado correctamente
        verify(service.typeOfUserRepository).deleteById(1L);
    }
}
