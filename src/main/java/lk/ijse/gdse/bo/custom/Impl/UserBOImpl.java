package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);


    @Override
    public ArrayList<UserDTO> getAllUserIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_id FROM user");

        ArrayList<Integer> userIds = new ArrayList<>();

        while (rst.next()) {
            userIds.add(rst.getInt(1));
        }

        return getAllUserIds();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(String.valueOf(id));
    }


}
