package controller;

import model.ProfesorDAO;
import model.ProfesorModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class ProfesorController {

    private ConsoleView consoleView;
    private ProfesorDAO profesorDAO;

    public ProfesorController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.profesorDAO = new ProfesorDAO(connection);
    }

    public ProfesorModel agregarProfesor(String nombre, String identificacion, String email, String departamento, String estado) {
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);

        try {
            profesorDAO.insert(profesor);
            consoleView.showMessage("Profesor insertado con ID: " + profesor.getId());
            return profesor;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar profesor: " + e.getMessage());
            return null;
        }
    }

    public boolean modificarProfesor(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);
        profesor.setId(id);

        try {
            profesorDAO.update(profesor);
            consoleView.showMessage("Profesor modificado");
            return true;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar profesor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProfesor(int id) {
        ProfesorModel profesor = null;
        try {
            profesor = profesorDAO.select(id);
            if (profesor != null) {
                profesorDAO.delete(id);
                consoleView.showMessage("Profesor eliminado");
                return true;
            } else {
                consoleView.errorMessage("Profesor no encontrado");
                return false;
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }

    public ProfesorModel consultarProfesor(int id) {
        ProfesorModel profesor = null;
        try {
            profesor = profesorDAO.select(id);
            if (profesor != null) {
                consoleView.consultarProfesor(profesor);
            } else {
                consoleView.errorMessage("Profesor no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar profesor: " + e.getMessage());
        }
        return profesor;
    }
}