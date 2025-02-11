package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.DeliveryBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DeliveryDAO;
import lk.ijse.gdse.dto.DeliveryDTO;
import lk.ijse.gdse.dto.ManageDTO;
import lk.ijse.gdse.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {

     DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);

    @Override
    public List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        ArrayList<DeliveryDTO> allData = deliveryDAO.getAllDelivery();

        List<DeliveryDTO> deliveryDTOList = new ArrayList<>();

        for (DeliveryDTO deliveryDTO : allData) {
            deliveryDTO = new DeliveryDTO(
                    deliveryDTO.getDelivery_id(),
                    deliveryDTO.getOrder_id(),
                    deliveryDTO.getDelivery_address(),
                    deliveryDTO.getDelivery_date(),
                    deliveryDTO.getDelivery_status(),
                    deliveryDTO.getEmployee_id()
            );
            deliveryDTOList.add(deliveryDTO);
        }
        return deliveryDTOList;
    }

    @Override
    public List<ManageDTO> getAllManageRecords() {
        return List.of();
    }

    @Override
    public boolean saveManage(ManageDTO manageDTO) {
        return false;
    }

    @Override
    public boolean saveDelivery(DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException {
        Delivery delivery = new Delivery();
        delivery.setDelivery_id(deliveryDTO.getDelivery_id());
        delivery.setOrder_id(deliveryDTO.getOrder_id());
        delivery.setDelivery_address(deliveryDTO.getDelivery_address());
        delivery.setDelivery_date(deliveryDTO.getDelivery_date());
        delivery.setDelivery_status(deliveryDTO.getDelivery_status());
        delivery.setEmployee_id(deliveryDTO.getEmployee_id());

        return deliveryDAO.save(delivery);

    }

    @Override
    public boolean updateDelivery(DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException {
        Delivery delivery = new Delivery();
        delivery.setDelivery_id(deliveryDTO.getDelivery_id());
        delivery.setOrder_id(deliveryDTO.getOrder_id());
        delivery.setDelivery_address(deliveryDTO.getDelivery_address());
        delivery.setDelivery_date(deliveryDTO.getDelivery_date());
        delivery.setDelivery_status(deliveryDTO.getDelivery_status());
        delivery.setEmployee_id(deliveryDTO.getEmployee_id());

        return deliveryDAO.update(delivery);

    }

    @Override
    public boolean deleteDelivery(int deliveryId) throws SQLException, ClassNotFoundException {
        return deliveryDAO.delete(String.valueOf(deliveryId));
    }
}
