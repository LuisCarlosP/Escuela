import controller.GrupoController;
import model.GrupoModel;
import model.GrupoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GrupoControllerTest {

    @Mock
    private GrupoDAO mockGrupoDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private GrupoController grupoController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarGrupo() throws SQLException {
        int id = 5;
        GrupoModel grupo = new GrupoModel(id, "Prueba", "Descripcion de prueba", "activo");

        when(mockGrupoDAO.select(anyInt())).thenReturn(grupo);

        GrupoModel result = grupoController.consultarGrupo(id);

        assertNotNull(result);
        mockView.showMessage("Exito: testConsultarGrupo pasó");
        verify(mockView).consultarGrupo(any(GrupoModel.class));
    }

    @Test
    public void testAgregarGrupo() throws SQLException {
        String nombre = "Prueba";
        String descripcion = "Descripcion de prueba";
        String estado = "Activo";
        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);

        doNothing().when(mockGrupoDAO).insert(any(GrupoModel.class));

        GrupoModel result = grupoController.agregarGrupo(nombre, descripcion, estado);

        assertEquals(grupo.getNombre(), result.getNombre());
        assertEquals(grupo.getDescripcion(), result.getDescripcion());
        assertEquals(grupo.getEstado(), result.getEstado());
        mockView.showMessage("Exito: testAgregarGrupo pasó");
        verify(mockView).showMessage("Grupo insertado con ID: " + result.getId());
    }

    @Test
    public void testEliminarGrupo() throws SQLException {
        int id = 7;
        when(mockGrupoDAO.delete(id)).thenReturn(true);

        boolean result = grupoController.eliminarGrupo(id);

        assertTrue(result);
        verify(mockView).showMessage("Grupo eliminado");
    }

    @Test
    public void testModificarGrupo() throws SQLException {
        int id = 5;
        String nombre = "Biologia";
        String descripcion = "Ciencias Naturales";
        String estado = "Activo";

        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);
        grupo.setId(id);

        doNothing().when(mockGrupoDAO).update(any(GrupoModel.class));

        boolean result = grupoController.modificarGrupo(id, nombre, descripcion, estado);

        assertTrue(result);
        verify(mockGrupoDAO).update(any(GrupoModel.class));
        verify(mockView).showMessage("Grupo modificado");
    }
}