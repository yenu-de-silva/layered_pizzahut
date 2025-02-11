package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.ItemBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.dto.tm.ItemTM;
import lk.ijse.gdse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item();
        item.setItem_id(itemDTO.getItem_id());
        item.setPrice(itemDTO.getPrice());
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());

        return itemDAO.save(item);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item();
        item.setItem_id(itemDTO.getItem_id());
        item.setPrice(itemDTO.getPrice());
        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());

        return itemDAO.update(item);
    }

    @Override
    public boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(itemId);
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        List<Item> allData = itemDAO.getAll();

        List<ItemDTO> itemTMList = new ArrayList<>();

        for (Item itemDTO : allData) {
            ItemDTO item = new ItemDTO(
                    itemDTO.getItem_id(),
                    itemDTO.getName(),
                    itemDTO.getQuantity(),
                    itemDTO.getPrice()
            );
            itemTMList.add(item);
        }
        return itemTMList;
    }

    @Override
    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) {
        return false;
    }

    @Override
    public ArrayList<String> getAllItemIds() {
        return null;
    }
}


