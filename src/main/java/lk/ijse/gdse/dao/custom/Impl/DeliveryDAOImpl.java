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
    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment(payment_id, order_id, payment_method, payment_date, amount, customer_id, order_name, quantity, payment_method1, payment_method2, payment_method3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    }

    @Override
    public boolean update(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET order_id=?, payment_method=?, payment_date=?, amount=?, customer_id=?, quantity=?, payment_method1=?, payment_method2=?, payment_method3=? WHERE payment_id=?");
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

    @Override
    public ArrayList<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        ArrayList<DeliveryDTO> deliveryDTOS = new ArrayList<>();

        while (rst.next()) {
            DeliveryDTO deliveryDTO = new DeliveryDTO(
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
}
