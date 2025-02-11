package lk.ijse.gdse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    int generateNewId() throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;

}
