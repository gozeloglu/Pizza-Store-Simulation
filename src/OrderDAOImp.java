/*
*
*
* This class implements the OrdeDAO.
* Concretes the methods how should make operation on order file.
* Also, this class has an arraylist to store orders.
* Adding, deleting, creating operations are being done here.
* If program should write on output file, methods writes the file.
*
* */



import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImp implements OrderDAO {
    private List<Order> orderArrayList = new ArrayList<>(); //Stores the all of information about orders.

    @Override
    public List<Order> getOrderArrayList() {
        return orderArrayList;
    }

    @Override
    public void createOrder(String orderID, String customerID, FileWriter fw, boolean status) throws IOException {
        Order newOrder = new Order(orderID, customerID);
        orderArrayList.add(newOrder);
        if ( status == true ) {
            fw.write("Order " + orderID + " created\n");
            fw.flush();
        }
    }

    @Override
    public void addDrink(String orderID, FileWriter fw, boolean status) throws IOException {

        for (int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( (orderArrayList.get(i).getOrderID()).equals(orderID) ) {
                orderArrayList.get(i).setOrderSoftDrink(1);
                if ( status == true ) {
                    fw.write("Drink added to order " + orderID + "\n");
                    fw.flush();
                }
                break;
            }
        }
    }

    @Override
    public void addPizza(Pizza pizza, String orderID, String pizzaName, FileWriter fw) throws IOException {
        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderArrayList.get(i).getOrderID().equals(orderID) && orderArrayList.get(i).getOrderPizza() != null ) {
                Order newOrder = new Order(orderID, pizza);
                orderArrayList.add(newOrder);
                fw.write(pizzaName + " pizza added to order " + orderID + "\n");
                fw.flush();
                break;
            } else if ( orderArrayList.get(i).getOrderID().equals(orderID) && orderArrayList.get(i).getOrderPizza() == null ) {
                orderArrayList.get(i).setOrderPizza(pizza);
                fw.write(pizzaName + " pizza added to order " + orderID + "\n");
                fw.flush();
            }
        }

    }

    @Override
    public void payCheck(String orderID, FileWriter fw) throws IOException {
        int total = 0;
        int tmp = 0;
        fw.write("PayCheck for order ");
        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderID.equals(orderArrayList.get(i).getOrderID())) {
                if ( tmp == 0 ) {
                    fw.write(orderArrayList.get(i).getOrderID() + "\n");
                    tmp++;
                }
                if ( orderArrayList.get(i).getOrderPizza() != null & orderArrayList.get(i).getOrderPizza().getDescription() != "" ) {
                    Operation operation = new Operation();
                    String tempPizza = operation.reversePizza(orderArrayList.get(i).getOrderPizza().getDescription());

                    int cost = orderArrayList.get(i).getOrderPizza().cost();
                    total += cost;
                    fw.write("\t" + tempPizza + cost + "$\n");
                }
            }
        }
        int drinkCost = 0;
        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderID.equals(orderArrayList.get(i).getOrderID()) ) {
                drinkCost += orderArrayList.get(i).getOrderSoftDrink();
            }
        }
        if ( drinkCost != 0 ) {
            fw.write("\tSoftDrink " + drinkCost + "$\n");
        }
        fw.write("\tTotal: " + (total + drinkCost) + "$\n");
        fw.flush();
    }

    @Override
    public void addPizzaFromFile(Pizza pizza, String id, String customerid) {
        Order newOrder = new Order(id, customerid, pizza);
        orderArrayList.add(newOrder);
    }

    @Override
    public void removeOrderID(String orderID, FileWriter fw) throws IOException {
        int counter = 0;
        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderArrayList.get(i).getOrderID().equalsIgnoreCase(orderID) ) {
                counter++;
            }
        }

        int i = 0;
        while ( i < counter ) {
            for ( int j = 0 ; j < orderArrayList.size() ; j++ ) {
                orderArrayList.remove(j);
                break;
            }
            i++;
        }

        fw.write( "Order " + orderID +" removed\n");
        fw.flush();
    }
}
