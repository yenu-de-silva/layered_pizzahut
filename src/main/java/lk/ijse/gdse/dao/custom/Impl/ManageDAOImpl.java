package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.ManageDAO;
import lk.ijse.gdse.dao.custom.ManageDAO;
import lk.ijse.gdse.entity.Manage;

import java.sql.SQLException;
import java.util.List;

public class ManageDAOImpl implements ManageDAO {
    @Override
    public List<Manage> getAll() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("select * from Manage");
    }

    @Override
    public boolean save(Manage dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into manage (manage_id, inventory_id, supplier_id, order_id, quantity , supplier_name , supplier_contact_name) values (?,?,?,?,?,?,?)",dto.getManageId(),dto.getInventoryId(),dto.getSupplierId(),dto.getOrderId(),dto.getQuantity(),dto.getSupplierName());
    }

    @Override
    public boolean update(Manage dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Manage search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
