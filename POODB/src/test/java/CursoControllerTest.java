import controller.CursoController;
import model.CursoModel;
import model.CursoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoControllerTest {

    @Mock
    private CursoDAO mockCursoDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarCurso() throws SQLException {
        int id = 5;
        CursoModel curso = new CursoModel(id, "Prueba", "Descripcion de prueba", "activo");

        when(mockCursoDAO.select(anyInt())).thenReturn(curso);

        CursoModel result = cursoController.consultarCurso(id);

        assertNotNull(result);
        mockView.showMessage("Exito: testConsultarCurso pasó");
        verify(mockView).consultarCurso(any(CursoModel.class));
    }

    @Test
    public void testAgregarCurso() throws SQLException {
        String nombre = "Prueba";
        String descripcion = "Descripcion de prueba";
        String estado = "Activo";
        CursoModel curso = new CursoModel(nombre, descripcion, estado);

        doNothing().when(mockCursoDAO).insert(any(CursoModel.class));

        CursoModel result = cursoController.agregarCurso(nombre, descripcion, estado);

        assertEquals(curso.getNombre(), result.getNombre());
        assertEquals(curso.getDescripcion(), result.getDescripcion());
        assertEquals(curso.getEstado(), result.getEstado());
        mockView.showMessage("Exito: testagregarCurso pasó");
        verify(mockView).showMessage("Curso insertado con ID: " + result.getId());
    }

    @Test
    public void testEliminarCurso() throws SQLException {
        int id = 7;
        when(mockCursoDAO.delete(id)).thenReturn(true);

        boolean result = cursoController.eliminarCurso(id);

        assertTrue(result);
        verify(mockView).showMessage("Curso eliminado");
    }

    @Test
    public void testModificarCurso() throws SQLException {
        int id = 5;
        String nombre = "Biologia";
        String descripcion = "Ciencias Naturales";
        String estado = "Activo";

        CursoModel curso = new CursoModel(nombre, descripcion, estado);
        curso.setId(id);

        doNothing().when(mockCursoDAO).update(any(CursoModel.class));

        boolean result = cursoController.modificarCurso(id, nombre, descripcion, estado);

        assertTrue(result);
        verify(mockCursoDAO).update(any(CursoModel.class));
        verify(mockView).showMessage("Curso modificado");
    }
}