package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from items");

        ArrayList<Item> itemDTOS = new ArrayList<>();

        while (rst.next()) {
            Item itemDTO = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }


    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item (item_id, name, quantity, price) VALUES (?, ?, ?, ?)",dto.getItem_id(),dto.getName(),dto.getQuantity(),dto.getPrice());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET name = ?, quantity = ?, price = ? WHERE item_id = ?",dto.getName(),dto.getQuantity(),dto.getPrice(),dto.getItem_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE item_id = ?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT MAX(item_id) FROM item");
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
