package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteDAO extends GenericDAO<EstudianteModel> {

    public EstudianteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(EstudianteModel estudiante) throws SQLException {
        String INSERT_SQL = "INSERT INTO Estudiante_lcpr (nombre, identificacion, email, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getEmail());
            stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmt.setString(5, estudiante.getEstado());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    estudiante.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(EstudianteModel estudiante) throws SQLException {
        String UPDATE_SQL = "UPDATE Estudiante_lcpr SET nombre = ?, identificacion = ?, email = ?, fecha_nacimiento = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getEmail());
            stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmt.setString(5, estudiante.getEstado());
            stmt.setInt(6, estudiante.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Estudiante_lcpr WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_SQL)) {
            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
            }
        }

        String DELETE_SQL = "DELETE FROM Estudiante_lcpr WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_SQL)) {
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }

        return true;
    }

    @Override
    public EstudianteModel select(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Estudiante_lcpr WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_SQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    EstudianteModel estudiante = new EstudianteModel();
                    estudiante.setId(rs.getInt("id"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setIdentificacion(rs.getString("identificacion"));
                    estudiante.setEmail(rs.getString("email"));
                    estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    estudiante.setEstado(rs.getString("estado"));
                    return estudiante;
                } else {
                    return null;
                }
            }
        }
    }
}