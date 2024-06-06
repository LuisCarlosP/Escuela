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

     public CursoController(ConsoleView consoleView ){
         this.consoleView = consoleView;
         Connection connection = conexion.getConnection();
         this.cursoDAO = new CursoDAO(connection);
     }

    public void agregarCurso (String nombre, int estado){
         CursoModel datos = new CursoModel(nombre, estado);

         try {
             cursoDAO.agregarCurso(datos);
             consoleView.showMessage("Datos insertados");
         }catch (SQLException e){
             consoleView.errorMessage("Datos no insertados");
         }
    }

    //public void agregarCurso (CursoModel objeto)
}
