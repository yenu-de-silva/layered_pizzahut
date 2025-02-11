package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.InventoryDAO;
import lk.ijse.gdse.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    @Override
    public List<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from inventory");

        ArrayList<Inventory> inventoryDTOS = new ArrayList<>();

        while (rst.next()) {
            Inventory inventoryDTO = new Inventory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5)
            );
            inventoryDTOS.add(inventoryDTO);
        }
        return inventoryDTOS;
    }

    @Override
    public boolean save(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO inventory(inventory_id, product_id, supplier_id, quantity, last_updated) VALUES (?, ?, ?, ?,?",dto.getInventory_id(),dto.getProduct_id(),dto.getSupplier_id(),dto.getQuantity(),dto.getLast_updated());
    }

    @Override
    public boolean update(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE inventory SET product_id=?, supplier_id=? , quantity=?, last_updated=? WHERE inventory_id=?",dto.getProduct_id(),dto.getSupplier_id(),dto.getQuantity(),dto.getLast_updated(),dto.getInventory_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM inventory WHERE inventory_id=?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT inventory_id FROM inventory ORDER BY inventory_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            int newId = Integer.parseInt(lastId) + 1;
            return Integer.parseInt(String.format("%04d", newId));
        }
        return Integer.parseInt("0001");
    }

    @Override
    public Inventory search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
