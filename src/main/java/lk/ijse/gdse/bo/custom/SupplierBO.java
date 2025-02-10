package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.SupplierDTO;
import lk.ijse.gdse.dto.tm.SupplierTM;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    List<SupplierTM> getAllSuppliers() throws SQLException, ClassNotFoundException;

    int getNextSupplierId() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierTM selectedSupplier) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;
}
