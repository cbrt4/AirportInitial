package repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface {

    <T> void add(T entity) throws SQLException;

    <T> void remove(T entity) throws SQLException;

    <T> void update(T entity) throws SQLException;

    <T> List<T> getAll() throws SQLException;
}
