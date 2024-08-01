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

    public CursoController(ConsoleView consoleView, CursoDAO cursoDAO) {
        this.consoleView = consoleView;
        this.cursoDAO = cursoDAO;
    }

    public CursoModel agregarCurso(String nombre, String descripcion, String estado) {
        CursoModel curso = new CursoModel(nombre, descripcion, estado);

        try {
            cursoDAO.insert(curso);
            consoleView.showMessage("Curso insertado con ID: " + curso.getId());
            return curso;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar curso: " + e.getMessage());
            return null;
        }
    }

    public boolean modificarCurso(int id, String nombre, String descripcion, String estado) {
        CursoModel curso = new CursoModel(nombre, descripcion, estado);
        curso.setId(id);

        try {
            cursoDAO.update(curso);
            consoleView.showMessage("Curso modificado");
            return true;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCurso(int id) {
        try {
            if (cursoDAO.delete(id)) {
                consoleView.showMessage("Curso eliminado");
                return true;
            } else {
                consoleView.errorMessage("Curso no encontrado");
                return false;
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public CursoModel consultarCurso(int id) {
        CursoModel curso = null;
        try {
            curso = cursoDAO.select(id);
            if (curso != null) {
                consoleView.consultarCurso(curso);
            } else {
                consoleView.errorMessage("Curso no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar curso: " + e.getMessage());
        }
        return curso;
    }
}