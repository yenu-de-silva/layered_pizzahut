package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.DeliveryDAO;
import lk.ijse.gdse.dto.DeliveryDTO;
import lk.ijse.gdse.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {

    @Override
    public ArrayList<Delivery> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM delivery");

        ArrayList<Delivery> deliveryDTOS = new ArrayList<>();

        while (rst.next()) {
            Delivery deliveryDTO = new Delivery(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getString(5),
                    rst.getString(6));
            deliveryDTOS.add(deliveryDTO);
        }
        return deliveryDTOS;
    }

    @Override
    public boolean save(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO delivery(delivery_id, order_id, delivery_address, delivery_date, delivery_status, employee_id) VALUES (?,?,?,?,?,?)",dto.getDelivery_id(),dto.getOrder_id(),dto.getDelivery_address(),dto.getDelivery_date(),dto.getDelivery_status(),dto.getEmployee_id());
    }

    @Override
    public boolean update(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE delivery SET order_id=?, delivery_address=?, delivery_date=?, delivery_status=?, employee_id=? WHERE delivery_id=?",dto.getOrder_id(),dto.getDelivery_address(),dto.getDelivery_date(),dto.getDelivery_status(),dto.getEmployee_id(),dto.getDelivery_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE payment_id=?", id);

    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return Integer.parseInt(String.format("P%03d", newIdIndex));
        }
        return Integer.parseInt("P001");
    }

    @Override
    public Delivery search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
