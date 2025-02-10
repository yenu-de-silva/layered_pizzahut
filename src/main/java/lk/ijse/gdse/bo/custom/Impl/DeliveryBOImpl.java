package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.DeliveryBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DeliveryDAO;
import lk.ijse.gdse.dto.ManageDTO;
import lk.ijse.gdse.dto.tm.DeliveryTM;
import lk.ijse.gdse.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {

    private final DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);

    @Override
    public List<DeliveryTM> getAllDelivery() throws SQLException, ClassNotFoundException {
        List<Delivery> allData = deliveryDAO.getAllDelivery();

        List<DeliveryTM> deliveryTMList = new ArrayList<>();

        for (Delivery delivery : allData) {
            DeliveryTM deliveryTM = new DeliveryTM(
                    delivery.getDelivery_id(),
                    delivery.getOrder_id(),
                    delivery.getDelivery_address(),
                    delivery.getDelivery_date(),
                    delivery.getDelivery_status(),
                    delivery.getEmployee_id()
            );
            deliveryTMList.add(deliveryTM);
        }
        return deliveryTMList;
    }

    @Override
    public List<ManageDTO> getAllManageRecords() {
        return List.of();
    }

    @Override
    public boolean saveManage(ManageDTO manageDTO) {
        return false;
    }
}
