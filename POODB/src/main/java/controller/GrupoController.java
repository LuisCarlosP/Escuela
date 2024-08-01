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

    public GrupoController(ConsoleView consoleView, GrupoDAO grupoDAO) {
        this.consoleView = consoleView;
        this.grupoDAO = grupoDAO;
    }
    public GrupoModel agregarGrupo(String nombre, String descripcion, String estado) {
        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);

        try {
            grupoDAO.insert(grupo);
            consoleView.showMessage("Grupo insertado con ID: " + grupo.getId());
            return grupo;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al insertar grupo: " + e.getMessage());
            return null;
        }
    }

    public boolean modificarGrupo(int id, String nombre, String descripcion, String estado) {
        GrupoModel grupo = new GrupoModel(nombre, descripcion, estado);
        grupo.setId(id);

        try {
            grupoDAO.update(grupo);
            consoleView.showMessage("Grupo modificado");
            return true;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al modificar grupo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarGrupo(int id) {
        try {
            if (grupoDAO.delete(id)) {
                consoleView.showMessage("Grupo eliminado");
                return true;
            } else {
                consoleView.errorMessage("Grupo no encontrado");
                return false;
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al eliminar grupo: " + e.getMessage());
            return false;
        }
    }

    public GrupoModel consultarGrupo(int id) {
        GrupoModel grupo = null;
        try {
            grupo = grupoDAO.select(id);
            if (grupo != null) {
                consoleView.consultarGrupo(grupo);
                return grupo;
            } else {
                consoleView.errorMessage("Grupo no encontrado");
                return null;
            }
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar grupo: " + e.getMessage());
            return null;
        }
    }
}