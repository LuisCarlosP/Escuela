package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL = "jdbc:mysql://45.88.196.5:3306/u484426513_poo224";
    private static final String USER = "u484426513_poo224";
    private static final String PASSWORD = "iH6D?ZF_7AykMRw";

    public static Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida");
        }catch (SQLException e){
            System.err.println("No hubo conexion" + e.getMessage());
        }

        return connection;
    }

}
