package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from user");

        ArrayList<User> userDTOS = new ArrayList<>();

        while (rst.next()) {
            User userDTO = new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<User> getAllUserIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_id FROM user");

        ArrayList<Integer> userIds = new ArrayList<>();

        while (rst.next()) {
            userIds.add(rst.getInt(1));
        }

        return getAllUserIds();
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user(user_id, username, password,email, role) VALUES (?,?,?,?,?)",dto.getUser_id(),dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getRole());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET username=?, password=?, email=? , role=? WHERE user_id=?",dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getRole(),dto.getUser_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM user WHERE user_id=?",id);

    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");

        if (rst.next()) {
            int lastId = rst.getInt(1);
            return lastId + 1;
        }
        return 1;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
