/*
*
* This class stores the information about order its field.
* Also, this class have constructor, accessor and mutator methods to
* make some changes on objects.
*
* */


public class Order {
    private String orderID;
    private String customerID;
    private int orderSoftDrink = 0;
    private Pizza orderPizza;

    public Order() {

    }
    public Order(String orderID, String customerID) {
        this.orderID = orderID;
        this.customerID = customerID;
    }

    public Order(String orderID, Pizza orderPizza) {
        this.orderID = orderID;
        this.orderPizza = orderPizza;
    }

    public Order(String orderID, String customerID, Pizza orderPizza) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderPizza = orderPizza;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Pizza getOrderPizza() {
        return orderPizza;
    }

    public int getOrderSoftDrink() {
        return orderSoftDrink;
    }

    public void setOrderSoftDrink(int cost) {
        this.orderSoftDrink += cost;
    }

    public void setOrderPizza(Pizza orderPizza) {
        this.orderPizza = orderPizza;
    }
}