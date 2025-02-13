package lk.ijse.gdse.dao;

import lk.ijse.gdse.bo.custom.Impl.DeliveryBOImpl;
import lk.ijse.gdse.bo.custom.Impl.InventoryBOImpl;
import lk.ijse.gdse.bo.custom.Impl.ManageBOImpl;
import lk.ijse.gdse.bo.custom.Impl.UserBOImpl;
import lk.ijse.gdse.dao.custom.Impl.*;
import lk.ijse.gdse.dao.custom.ProductDAO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Salary;

import java.sql.SQLException;
import java.util.List;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER,DEPARTMENT,EMPLOYEE,PAYMENT,PRODUCT,INVENTORY,SUPPLIER,ITEM,ORDER,SALARY,USER,DELIVERY,MANAGE, ORDER_DETAIL
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
                case DEPARTMENT:
                    return new DepartmentDAOImpl();
                    case EMPLOYEE:
                        return new EmployeeDAOImpl();
                        case PAYMENT:
                            return new PaymentDAOImpl();
                            case PRODUCT:
                                return new ProductDAOImpl();
                                    case INVENTORY:
                                        return new InventoryDAOImpl();
                                        case SUPPLIER:
                                            return new SupplierDAOImpl();
                                            case ITEM:
                                                return new ItemDAOImpl();
                                                case ORDER:
                                                    return new OrderDAOImpl();
                                                    case SALARY:
                                                        return new SalaryDAOImpl();
                                                        case USER:
                                                            return new UserDAOImpl();
                                                            case DELIVERY:
                                                                return new DeliveryDAOImpl();
                                                                case MANAGE:
                                                                    return new ManageDAOImpl();
                                                                    case ORDER_DETAIL:
                                                                        return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }

}
