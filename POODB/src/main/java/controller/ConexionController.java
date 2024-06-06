package controller;

import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionController {
    private ConsoleView consoleView;

    public ConexionController(ConsoleView consoleView){
        this.consoleView = consoleView;
    }

    public void openConnection(){
        Connection connection = conexion.getConnection();

        if ( connection != null ){
            try {
                connection.close();
                consoleView.showMessage("Conexion Establecida");
            }catch (SQLException e){
                consoleView.errorMessage("Conexion no establecida " + e.getMessage());
            }
        }
    }
}
