package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupoDAO extends GenericDAO<GrupoModel> {

    public GrupoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(GrupoModel grupo) throws SQLException {
        String INSERT_SQL = "INSERT INTO Grupo_lcpr (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setString(3, grupo.getEstado());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    grupo.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(GrupoModel grupo) throws SQLException {
        String UPDATE_SQL = "UPDATE Grupo_lcpr SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setString(3, grupo.getEstado());
            stmt.setInt(4, grupo.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Grupo_lcpr WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_SQL)) {
            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
            }
        }

        String DELETE_SQL = "DELETE FROM Grupo_lcpr WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_SQL)) {
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }

        return true;
    }

    @Override
    public GrupoModel select(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Grupo_lcpr WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_SQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    GrupoModel grupo = new GrupoModel();
                    grupo.setId(rs.getInt("id"));
                    grupo.setNombre(rs.getString("nombre"));
                    grupo.setDescripcion(rs.getString("descripcion"));
                    grupo.setEstado(rs.getString("estado"));
                    return grupo;
                } else {
                    return null;
                }
            }
        }
    }
}