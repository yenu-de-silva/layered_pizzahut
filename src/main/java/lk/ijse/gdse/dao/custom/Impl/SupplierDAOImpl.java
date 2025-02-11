package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.SupplierDAO;
import lk.ijse.gdse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from inventory");

        ArrayList<Supplier> supplierDTOS = new ArrayList<>();

        while (rst.next()) {
            Supplier supplierDTO = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into supplier(supplier_id, supplier_name,contact_name, contact_number, address) values (?,?,?,?,?)",dto.getSupplier_id(),dto.getSupplier_name(),dto.getContact_name(),dto.getContact_number(),dto.getAddress());
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update supplier set Supplier_name=?,contact_name=?, contact_number=?, address=? where supplier_id=?",dto.getSupplier_name(),dto.getContact_name(),dto.getContact_number(),dto.getAddress(),dto.getSupplier_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("select supplier_id from supplier",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("select supplier_id from supplier order by supplier_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return Integer.parseInt(String.format("S%03d", newIdIndex));
        }
        return Integer.parseInt("S001");
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
