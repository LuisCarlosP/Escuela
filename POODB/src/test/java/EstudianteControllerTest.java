import controller.EstudianteController;
import model.EstudianteModel;
import model.EstudianteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudianteControllerTest {

    @Mock
    private EstudianteDAO mockEstudianteDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private EstudianteController estudianteController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarEstudiante() throws SQLException {
        int id = 5;
        EstudianteModel estudiante = new EstudianteModel(id, "Prueba", "123456789", "prueba@example.com", new Date(), "activo");

        when(mockEstudianteDAO.select(anyInt())).thenReturn(estudiante);

        EstudianteModel result = estudianteController.consultarEstudiante(id);

        assertNotNull(result);
        mockView.showMessage("Éxito: testConsultarEstudiante pasó");
        verify(mockView).consultarEstudiante(any(EstudianteModel.class));
    }

    @Test
    public void testAgregarEstudiante() throws SQLException {
        String nombre = "Prueba";
        String identificacion = "123456789";
        String email = "prueba@example.com";
        Date fechaNacimiento = new Date();
        String estado = "Activo";
        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, fechaNacimiento, estado);

        doNothing().when(mockEstudianteDAO).insert(any(EstudianteModel.class));

        EstudianteModel result = estudianteController.agregarEstudiante(nombre, identificacion, email, fechaNacimiento, estado);

        assertEquals(estudiante.getNombre(), result.getNombre());
        assertEquals(estudiante.getIdentificacion(), result.getIdentificacion());
        assertEquals(estudiante.getEmail(), result.getEmail());
        assertEquals(estudiante.getFechaNacimiento(), result.getFechaNacimiento());
        assertEquals(estudiante.getEstado(), result.getEstado());
        mockView.showMessage("Éxito: testAgregarEstudiante pasó");
        verify(mockView).showMessage("Estudiante insertado con ID: " + result.getId());
    }

    @Test
    public void testEliminarEstudiante() throws SQLException {
        int id = 4;
        when(mockEstudianteDAO.delete(id)).thenReturn(true);

        boolean result = estudianteController.eliminarEstudiante(id);

        assertTrue(result);
        verify(mockView).showMessage("Estudiante eliminado");
    }

    @Test
    public void testModificarEstudiante() throws SQLException {
        int id = 5;
        String nombre = "Biologia";
        String identificacion = "987654321";
        String email = "biologia@example.com";
        Date fechaNacimiento = new Date();
        String estado = "Activo";

        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, fechaNacimiento, estado);
        estudiante.setId(id);

        when(mockEstudianteDAO.select(id)).thenReturn(estudiante);
        doNothing().when(mockEstudianteDAO).update(any(EstudianteModel.class));

        boolean result = estudianteController.modificarEstudiante(id, nombre, identificacion, email, fechaNacimiento, estado);

        assertTrue(result);
        verify(mockEstudianteDAO).update(any(EstudianteModel.class));
        verify(mockView).showMessage("Estudiante modificado");
    }
}