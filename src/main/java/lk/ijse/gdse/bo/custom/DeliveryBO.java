package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.ManageDTO;
import lk.ijse.gdse.dto.tm.DeliveryTM;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryBO extends SuperBO {
    List<DeliveryTM> getAllDelivery() throws SQLException, ClassNotFoundException;


    List<ManageDTO> getAllManageRecords();

    boolean saveManage(ManageDTO manageDTO);
}
