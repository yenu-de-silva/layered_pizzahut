package lk.ijse.gdse.bo;

import lk.ijse.gdse.bo.custom.DepartmentBO;
import lk.ijse.gdse.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        CUSTOMER,DEPARTMENT,EMPLOYEE,PAYMENT,PRODUCT,INVENTORY,SUPPLIER,ITEM,ORDER,SALARY,USER,DELIVERY,MANAGE,ORDERDETAILS
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
                case DEPARTMENT:
                    return new DepartmentBOImpl();
                    case EMPLOYEE:
                        return new EmployeeBOImpl();
                         case PAYMENT:
                             return new PaymentBOImpl();
                             case PRODUCT:
                                 return new ProductBOImpl();
                                 case INVENTORY:
                                     return new InventoryBOImpl();
                                     case SUPPLIER:
                                         return new SupplierBOImpl();
                                         case ITEM:
                                             return new ItemBOImpl();
                                             case ORDER:
                                                 return new OrderBOImpl();
                                                 case SALARY:
                                                     return new SupplierBOImpl();
                                                     case USER:
                                                         return new UserBOImpl();
                                                         case DELIVERY:
                                                             return new DeliveryBOImpl();
                                                             case MANAGE:
                                                                 return new ManageBOImpl();
                                                                 case ORDERDETAILS:
                                                                     return new OrderDetailsBOImpl();



            default:
                return null;
        }
    }
}
