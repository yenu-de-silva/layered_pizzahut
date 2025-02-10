package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends SupplierDAO{
    boolean save(Item dto) throws SQLException, ClassNotFoundException;

    boolean update(Item dto) throws SQLException, ClassNotFoundException;
}
