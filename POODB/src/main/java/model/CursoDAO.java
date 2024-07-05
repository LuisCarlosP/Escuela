package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAO extends GenericDAO<CursoModel> {

    public CursoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(CursoModel curso) throws SQLException {
        String INSERT_SQL = "INSERT INTO Curso_lcpr (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getEstado());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(CursoModel curso) throws SQLException {
        String UPDATE_SQL = "UPDATE Curso_lcpr SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getEstado());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Curso_lcpr WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_SQL)) {
            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
            }
        }

        String DELETE_SQL = "DELETE FROM Curso_lcpr WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_SQL)) {
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }

        return true;
    }

    @Override
    public CursoModel select(int id) throws SQLException {
        String SELECT_SQL = "SELECT * FROM Curso_lcpr WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_SQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CursoModel curso = new CursoModel();
                    curso.setId(rs.getInt("id"));
                    curso.setNombre(rs.getString("nombre"));
                    curso.setDescripcion(rs.getString("descripcion"));
                    curso.setEstado(rs.getString("estado"));
                    return curso;
                } else {
                    return null;
                }
            }
        }
    }
}