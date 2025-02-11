package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.InventoryBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.InventoryDAO;
import lk.ijse.gdse.dto.InventoryDTO;
import lk.ijse.gdse.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);

    @Override
    public List<InventoryDTO> getAllInventoryItems() throws SQLException, ClassNotFoundException {
        List<Inventory> allData = inventoryDAO.getAll();

        List<InventoryDTO> inventoryTMList = new ArrayList<>();

        for (Inventory inventory : allData) {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    inventory.getInventory_id(),
                    inventory.getProduct_id(),
                    inventory.getSupplier_id(),
                    inventory.getQuantity(),
                    inventory.getLast_updated()
            );
            inventoryTMList.add(inventoryDTO);
        }
        return inventoryTMList;
    }

    @Override
    public int getNextInventoryId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.generateNewId();
    }

    @Override
    public boolean saveInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        Inventory inventory = new Inventory();
        inventory.setInventory_id(inventoryDTO.getInventory_id());
        inventory.setProduct_id(inventoryDTO.getProduct_id());
        inventory.setSupplier_id(inventoryDTO.getSupplier_id());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setLast_updated(inventoryDTO.getLast_updated());

        return inventoryDAO.save(inventory);

    }

    @Override
    public boolean updateInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        Inventory inventory = new Inventory();
        inventory.setInventory_id(inventoryDTO.getInventory_id());
        inventory.setProduct_id(inventoryDTO.getProduct_id());
        inventory.setSupplier_id(inventoryDTO.getSupplier_id());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setLast_updated(inventoryDTO.getLast_updated());

        return inventoryDAO.update(inventory);
    }

    @Override
    public boolean deleteInventory(String i) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(i);
    }
}
