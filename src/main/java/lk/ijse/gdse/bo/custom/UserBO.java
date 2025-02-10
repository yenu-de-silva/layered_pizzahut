package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    ArrayList<UserDTO> getAllUserIds() throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean deleteUser(int i) throws SQLException, ClassNotFoundException;
}
