package controller;

import model.ProfesorDAO;
import model.ProfesorModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ProfesorController {
    private ConsoleView consoleView;
    private ProfesorDAO profesorDAO;

    public ProfesorController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.profesorDAO = new ProfesorDAO(connection);
    }

    public void agregarProfesor(String nombre, String identificacion, String email, String departamento, String estado) {
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);

        try {
            profesorDAO.insert(profesor);
            consoleView.showMessage("Profesor insertado con ID: " + profesor.getId());
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar profesor: " + e.getMessage());
        }
    }

    public void modificarProfesor(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, departamento, estado);
        profesor.setId(id);

        try {
            profesorDAO.update(profesor);
            consoleView.showMessage("Profesor modificado");
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar profesor: " + e.getMessage());
        }
    }

    public void eliminarProfesor(int id) {
        try {
            if (profesorDAO.delete(id)) {
                consoleView.showMessage("Profesor eliminado");
            } else {
                consoleView.errorMessage("Profesor no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar profesor: " + e.getMessage());
        }
    }

    public void consultarProfesor(int id) {
        try {
            ProfesorModel profesor = profesorDAO.select(id);
            if (profesor != null) {
                consoleView.consultarProfesor(profesor);
            } else {
                consoleView.errorMessage("Profesor no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar profesor: " + e.getMessage());
        }
    }
}