package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDAO<T> {
    protected Connection connection;

    public GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void insert(T entity) throws SQLException;

    public abstract void update(T entity) throws SQLException;

    public abstract boolean delete(int id) throws SQLException;

    public abstract T select(int id) throws SQLException;
}