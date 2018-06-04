/*
*
* This interface class includes some of method signatures to make necessary operations.
* Class implements from OrderDAOImp
*
*
* */

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface OrderDAO {
    List<Order> getOrderArrayList(); //Returns the orderArrayList to access any other class.
    void createOrder(String orderID, String customerID, FileWriter fw, boolean status) throws IOException;
    void addDrink(String orderID, FileWriter fw, boolean status) throws IOException;
    void addPizza(Pizza pizza, String orderID, String pizzaName, FileWriter fw) throws IOException;
    void payCheck(String orderID, FileWriter fw) throws IOException;
    void addPizzaFromFile(Pizza pizza,String id, String customerid);
    void removeOrderID(String orderID, FileWriter fw) throws IOException;
}