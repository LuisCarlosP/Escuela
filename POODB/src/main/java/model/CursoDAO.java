package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoDAO {

    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarCurso (CursoModel objeto) throws SQLException {
        String query = "INSERT INTO `curso`( `nombre`, `estado`) VALUES ( ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setInt(2, objeto.getEstado());
            stmt.executeUpdate();
        }
    }



}


/*
DELETE FROM `curso` WHERE 0
UPDATE `curso` SET `id`='[value-1]',`nombre`='[value-2]',`estado`='[value-3]' WHERE 1
INSERT INTO `curso`(`id`, `nombre`, `estado`) VALUES ('[value-1]','[value-2]','[value-3]')
SELECT `id`, `nombre`, `estado` FROM `curso` WHERE 1

*/
