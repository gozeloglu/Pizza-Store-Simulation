/*
 *
 * This interface class includes some of method signatures to make necessary operations.
 * Class implements from CustomerDAOImp
 *
 *
 * */


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomerArrayList();  //Returns the customerArrayList to access any other methods.
    void addCustomer(String idCustomer, String nameCustomer, String surnameCustomer, String phoneCustomer, String addressCustomer, FileWriter fw, boolean status) throws  IOException;
    void removeByID(String customerID, FileWriter fw, OrderDAOImp orderDAOImp) throws IOException;
    void customerList(FileWriter fw) throws IOException;

}
