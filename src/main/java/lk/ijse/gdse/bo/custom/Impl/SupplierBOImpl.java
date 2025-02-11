package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.SupplierBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.SupplierDAO;
import lk.ijse.gdse.dto.SupplierDTO;
import lk.ijse.gdse.dto.tm.SupplierTM;
import lk.ijse.gdse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public List<SupplierTM> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Supplier> allData = supplierDAO.getAll();

        List<SupplierTM> supplierTMList = new ArrayList<>();

        for (Supplier supplier : allData) {
            SupplierTM supplierTM = new SupplierTM(
                    supplier.getSupplier_id(),
                    supplier.getSupplier_name(),
                    supplier.getContact_name(),
                    supplier.getContact_number(),
                    supplier.getAddress()
            );
            supplierTMList.add(supplierTM);
        }
        return supplierTMList;

    }

    @Override
    public int getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier();
        supplier.setSupplier_id(supplierDTO.getSupplier_id());
        supplier.setSupplier_name(supplierDTO.getSupplier_name());
        supplier.setContact_name(supplierDTO.getContact_name());
        supplier.setContact_number(supplierDTO.getContact_number());
        supplier.setAddress(supplierDTO.getAddress());

        return supplierDAO.save(supplier);
    }

    @Override
    public boolean updateSupplier(SupplierTM selectedSupplier) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier();
        supplier.setSupplier_id(selectedSupplier.getSupplier_id());
        supplier.setSupplier_name(selectedSupplier.getSupplier_name());
        supplier.setContact_name(selectedSupplier.getContact_name());
        supplier.setContact_number(selectedSupplier.getContact_number());
        supplier.setAddress(selectedSupplier.getAddress());

        return supplierDAO.update(supplier);
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }
}
