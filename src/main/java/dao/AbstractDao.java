package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
    protected Connection connection;
    protected String sql;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(T entity) {
        sql = getUpdateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(T entity) {
        sql = getCreateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForCreate(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int key) {
        sql = getDeleteQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForDelete(statement, key);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectAllQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected void prepareStatementForDelete(PreparedStatement statement, int key) {
        try {
            statement.setInt(1, key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getSelectAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity);
}
