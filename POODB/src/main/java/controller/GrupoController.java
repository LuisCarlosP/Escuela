package controller;

import model.GrupoDAO;
import model.GrupoModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class GrupoController {

    private ConsoleView consoleView;
    private GrupoDAO grupoDAO;

    public GrupoController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.grupoDAO = new GrupoDAO(connection);
    }

    public void agregarGrupo(String nombre, String descripcion, String estado) {
        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);

        try {
            grupoDAO.insert(grupo);
            consoleView.showMessage("Grupo insertado con ID: " + grupo.getId());
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar grupo: " + e.getMessage());
        }
    }


    public void modificarGrupo(int id, String nombre, String descripcion, String estado) {
        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);
        grupo.setId(id);

        try {
            grupoDAO.update(grupo);
            consoleView.showMessage("Grupo modificado");
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar grupo: " + e.getMessage());
        }
    }

    public void eliminarGrupo(int id) {
        try {
            if (grupoDAO.delete(id)) {
                consoleView.showMessage("Grupo eliminado");
            } else {
                consoleView.errorMessage("Grupo no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar grupo: " + e.getMessage());
        }
    }

    public void consultarGrupo(int id) {
        try {
            GrupoModel grupo = grupoDAO.select(id);
            if (grupo != null) {
                consoleView.consultarGrupo(grupo);
            } else {
                consoleView.errorMessage("Grupo no encontrado");
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar grupo: " + e.getMessage());
        }
    }
}