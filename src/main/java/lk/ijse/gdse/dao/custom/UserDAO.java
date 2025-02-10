package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    List<User> getAllUserIds() throws SQLException, ClassNotFoundException;
}
