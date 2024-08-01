import controller.ProfesorController;
import model.ProfesorModel;
import model.ProfesorDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorControllerTest {

    @Mock
    private ProfesorDAO mockProfesorDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private ProfesorController profesorController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarProfesor() throws SQLException {
        int id = 5;
        ProfesorModel profesor = new ProfesorModel(id, "Prueba", "123456789", "prueba@example.com", "Ciencias", "activo");

        when(mockProfesorDAO.select(anyInt())).thenReturn(profesor);

        ProfesorModel result = profesorController.consultarProfesor(id);

        assertNotNull(result);
        mockView.showMessage("Éxito: testConsultarProfesor pasó");
        verify(mockView).consultarProfesor(any(ProfesorModel.class));
    }

    @Test
    public void testAgregarProfesor() throws SQLException {
        String nombre = "Prueba";
        String identificacion = "123456789";
        String email = "prueba@example.com";
        String departamento = "Ciencias";
        String estado = "Activo";
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);

        doNothing().when(mockProfesorDAO).insert(any(ProfesorModel.class));

        ProfesorModel result = profesorController.agregarProfesor(nombre, identificacion, email, departamento, estado);

        assertEquals(profesor.getNombre(), result.getNombre());
        assertEquals(profesor.getIdentificacion(), result.getIdentificacion());
        assertEquals(profesor.getEmail(), result.getEmail());
        assertEquals(profesor.getDepartamento(), result.getDepartamento());
        assertEquals(profesor.getEstado(), result.getEstado());
        mockView.showMessage("Éxito: testAgregarProfesor pasó");
        verify(mockView).showMessage("Profesor insertado con ID: " + result.getId());
    }

    @Test
    public void testEliminarProfesor() throws SQLException {
        int id = 4;
        when(mockProfesorDAO.delete(id)).thenReturn(true);

        boolean result = profesorController.eliminarProfesor(id);

        assertTrue(result);
        verify(mockView).showMessage("Profesor eliminado");
    }

    @Test
    public void testModificarProfesor() throws SQLException {
        int id = 5;
        String nombre = "Matematicas";
        String identificacion = "987654321";
        String email = "matematicas@example.com";
        String departamento = "Matematicas";
        String estado = "Activo";

        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);
        profesor.setId(id);

        when(mockProfesorDAO.select(id)).thenReturn(profesor);
        doNothing().when(mockProfesorDAO).update(any(ProfesorModel.class));

        boolean result = profesorController.modificarProfesor(id, nombre, identificacion, email, departamento, estado);

        assertTrue(result);
        verify(mockProfesorDAO).update(any(ProfesorModel.class));
        verify(mockView).showMessage("Profesor modificado");
    }
}