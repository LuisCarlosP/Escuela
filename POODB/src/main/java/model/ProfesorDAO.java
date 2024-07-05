package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO extends GenericDAO<ProfesorModel> {

    public ProfesorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(ProfesorModel profesor) throws SQLException {
        String INSERT_SQL = "INSERT INTO Profesor_lcpr (nombre, identificacion, email, departamento, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, profesor.getNombre());
            stmt.setString(2, profesor.getIdentificacion());
            stmt.setString(3, profesor.getEmail());
            stmt.setString(4, profesor.getDepartamento());
            stmt.setString(5, profesor.getEstado());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    profesor.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(ProfesorModel profesor) throws SQLException {
        String UPDATE_SQL = "UPDATE Profesor_lcpr SET nombre = ?, identificacion = ?, email = ?, departamento = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, profesor.getNombre());
            stmt.setString(2, profesor.getIdentificacion());
            stmt.setString(3, profesor.getEmail());
            stmt.setString(4, profesor.getDepartamento());
            stmt.setString(5, profesor.getEstado());
            stmt.setInt(6, profesor.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Profesor_lcpr WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_SQL)) {
            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.next()) {
                    return false; // The record doesn't exist
                }
            }
        }

        String DELETE_SQL = "DELETE FROM Profesor_lcpr WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_SQL)) {
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }

        return true; // The record was deleted
    }

    @Override
    public ProfesorModel select(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Profesor_lcpr WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_SQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProfesorModel profesor = new ProfesorModel();
                    profesor.setId(rs.getInt("id"));
                    profesor.setNombre(rs.getString("nombre"));
                    profesor.setIdentificacion(rs.getString("identificacion"));
                    profesor.setEmail(rs.getString("email"));
                    profesor.setDepartamento(rs.getString("departamento"));
                    profesor.setEstado(rs.getString("estado"));
                    return profesor;
                } else {
                    return null;
                }
            }
        }
    }
}