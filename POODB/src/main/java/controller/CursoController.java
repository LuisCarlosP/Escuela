package controller;

import model.CursoDAO;
import model.CursoModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class CursoController {

    private ConsoleView consoleView;
    private CursoDAO cursoDAO;

    public CursoController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.cursoDAO = new CursoDAO(connection);
    }

    public void agregarCurso(String nombre, String descripcion, String estado) {
        CursoModel curso = new CursoModel(nombre, descripcion, estado);

        try {
            cursoDAO.insert(curso);
            consoleView.showMessage("Curso insertado con ID: " + curso.getId());
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar curso: " + e.getMessage());
        }
    }

    public void modificarCurso(int id, String nombre, String descripcion, String estado) {
        CursoModel curso = new CursoModel(nombre, descripcion, estado);
        curso.setId(id);

        try {
            cursoDAO.update(curso);
            consoleView.showMessage("Curso modificado");
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar curso: " + e.getMessage());
        }
    }

    public void eliminarCurso(int id) {
        try {
            if (cursoDAO.delete(id)) {
                consoleView.showMessage("Curso eliminado");
            } else {
                consoleView.errorMessage("Curso no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar curso: " + e.getMessage());
        }
    }

    public void consultarCurso(int id) {
        try {
            CursoModel curso = cursoDAO.select(id);
            if (curso != null) {
                consoleView.consultarCurso(curso);
            } else {
                consoleView.errorMessage("Curso no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar curso: " + e.getMessage());
        }
    }
}