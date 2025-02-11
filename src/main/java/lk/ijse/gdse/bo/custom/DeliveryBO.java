package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.DeliveryDTO;
import lk.ijse.gdse.dto.ManageDTO;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryBO extends SuperBO {
    List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException;


    List<ManageDTO> getAllManageRecords();

    boolean saveManage(ManageDTO manageDTO);

    boolean saveDelivery(DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException;

    boolean updateDelivery(DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException;

    boolean deleteDelivery(int deliveryId) throws SQLException, ClassNotFoundException;

}
