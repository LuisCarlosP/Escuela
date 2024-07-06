package controller;

import model.EstudianteDAO;
import model.EstudianteModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EstudianteController {

    private ConsoleView consoleView;
    private EstudianteDAO estudianteDAO;

    public EstudianteController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.estudianteDAO = new EstudianteDAO(connection);
    }

    public EstudianteModel agregarEstudiante(String nombre, String identificacion, String email, Date fechaNacimiento, String estado) {
        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, fechaNacimiento, estado);

        try {
            estudianteDAO.insert(estudiante);
            consoleView.showMessage("Estudiante insertado con ID: " + estudiante.getId());
            return estudiante;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar estudiante: " + e.getMessage());
            return null;
        }
    }

    public boolean modificarEstudiante(int id, String nombre, String identificacion, String email, Date fechaNacimiento, String estado) {
        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, fechaNacimiento, estado);
        estudiante.setId(id);

        try {
            estudianteDAO.update(estudiante);
            consoleView.showMessage("Estudiante modificado");
            return true;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarEstudiante(int id) {
        EstudianteModel estudiante = null;
        try {
            estudiante = estudianteDAO.select(id);
            if (estudiante != null) {
                estudianteDAO.delete(id);
                return true;
            } else {
                consoleView.errorMessage("Estudiante no encontrado");
                return false;
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }

    public EstudianteModel consultarEstudiante(int id) {
        EstudianteModel estudiante = null;
        try {
            estudiante = estudianteDAO.select(id);
            if (estudiante != null) {
                consoleView.consultarEstudiante(estudiante);
            } else {
                consoleView.errorMessage("Estudiante no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar estudiante: " + e.getMessage());
        }
        return estudiante;
    }
}