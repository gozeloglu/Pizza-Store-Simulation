/*
 *
 * This class stores the information about customer its field.
 * Also, this class have constructor, accessor and mutator methods to
 * make some changes on objects.
 *
 * */


public class Customer {
    private String customerID;
    private String customerName;
    private String customerSurname;
    private String customerPhoneNumber;
    private String customerAddress;

    public Customer() {

    }

    public Customer(String customerID, String customerName, String customerSurname,
                    String customerPhoneNumber, String customerAddress) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerSurname =customerSurname;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

}