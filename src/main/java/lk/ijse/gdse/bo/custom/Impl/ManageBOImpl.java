package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.ManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.dao.custom.ManageDAO;
import lk.ijse.gdse.dto.DepartmentDTO;
import lk.ijse.gdse.dto.ManageDTO;
import lk.ijse.gdse.entity.Department;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.entity.Manage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageBOImpl implements ManageBO {

    ManageDAO manageDAO = (ManageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MANAGE);


    @Override
    public List<ManageDTO> getAllManageRecords() throws SQLException, ClassNotFoundException {
        List<Manage> allData = manageDAO.getAll();

        List<ManageDTO> manageTMList = new ArrayList<>();

        for (Manage manage : allData) {
                ManageDTO manageDTO = new ManageDTO(
                    manage.getManageId(),
                    manage.getInventoryId(),
                    manage.getSupplierId(),
                    manage.getOrderId(),
                    manage.getQuantity(),
                    manage.getSupplierName(),
                    manage.getSupplierContactName()
            );
            manageTMList.add(manageDTO);
        }
        return (ArrayList<ManageDTO>) manageTMList;
    }

    @Override
    public boolean saveManage(ManageDTO manageDTO) throws SQLException, ClassNotFoundException {
        Manage manage = new Manage();
        manage.setManageId(manageDTO.getManageId());
        manage.setInventoryId(manageDTO.getInventoryId());
        manage.setSupplierId(manageDTO.getSupplierId());
        manage.setOrderId(manageDTO.getOrderId());
        manage.setQuantity(manageDTO.getQuantity());
        manage.setSupplierName(manageDTO.getSupplierName());
        manage.setSupplierContactName(manageDTO.getSupplierContactName());

        return manageDAO.save(manage);    }
}
