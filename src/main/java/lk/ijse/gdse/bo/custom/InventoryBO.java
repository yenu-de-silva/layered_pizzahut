package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface InventoryBO extends SuperBO {

    List<InventoryDTO> getAllInventoryItems() throws SQLException, ClassNotFoundException;

    int getNextInventoryId() throws SQLException, ClassNotFoundException;

    boolean saveInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean updateInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean deleteInventory(String i) throws SQLException, ClassNotFoundException;
}
