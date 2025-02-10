package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.dto.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException;

    List<ItemTM> getAllItems() throws SQLException, ClassNotFoundException;

    boolean reduceQty(OrderDetailsDTO orderDetailsDTO);

    ArrayList<String> getAllItemIds();
}
