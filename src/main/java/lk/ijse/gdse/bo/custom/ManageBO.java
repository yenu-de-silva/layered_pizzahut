package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.ManageDTO;

import java.sql.SQLException;
import java.util.List;

public interface ManageBO extends SuperBO {
    List<ManageDTO> getAllManageRecords() throws SQLException, ClassNotFoundException;

    boolean saveManage(ManageDTO manageDTO) throws SQLException, ClassNotFoundException;
}
