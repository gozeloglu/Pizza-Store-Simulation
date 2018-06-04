/*
*
* This class implements the CustomerDAO.
* Concretes the methods how should make operation on order file.
* Also, this class has an arraylist to store customers.
* Adding, deleting, creating operations are being done here.
* If program should write on output file, methods writes the file.
*
*
* */


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {
    private List<Customer> customerArrayList = new ArrayList<>();

    @Override
    public List<Customer> getCustomerArrayList() {
        return this.customerArrayList;
    }

    @Override
    public void addCustomer(String idCustomer, String nameCustomer,
                            String surnameCustomer, String phoneCustomer,
                            String addressCustomer, FileWriter fw, boolean status) throws IOException {

        Customer newCustomer = new Customer(idCustomer, nameCustomer, surnameCustomer, phoneCustomer, addressCustomer);
        this.customerArrayList.add(newCustomer);


        if ( status == true ) {
            fw.write("Customer " + customerArrayList.get(customerArrayList.size()-1).getCustomerID()  + " " + nameCustomer + " added\n");
            fw.flush();
        }
    }

    @Override
    public void removeByID (String customerID, FileWriter fw, OrderDAOImp orderDAOImp) throws IOException   {

        List<Order> orderArrayList = orderDAOImp.getOrderArrayList();

        int count = 0;
        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderArrayList.get(i).getCustomerID() != null && orderArrayList.get(i).getCustomerID().equals(customerID) ) {
                count++;
            }
        }

        int j = 0;
        while ( j < count ) {
            for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
                if ( orderArrayList.get(i).getCustomerID().equals(customerID) ) {
                    orderArrayList.remove(i);
                    break;
                }
            }
            j++;
        }


        int counter = 0, arraySize = customerArrayList.size();
        for ( int i = 0 ; i < customerArrayList.size() ; i++ ) {
            if ( customerID.equals(customerArrayList.get(i).getCustomerID()) ) {
                fw.write("Customer " + customerID + " " + customerArrayList.get(i).getCustomerName() + " removed\n");
                customerArrayList.remove(i);
                break;
            } else {
                counter++;
            }
        }
        if ( counter == arraySize ) {
            fw.write("You cannot remove non-existing customer\n");
            fw.flush();
        } else {

        }
    }

    @Override
    public void customerList(FileWriter fw) throws IOException {
        List<String> names = new ArrayList<>();

        for ( int i = 0 ; i < customerArrayList.size() ; i++ ) {
            names.add(customerArrayList.get(i).getCustomerName());
        }


        Collections.sort(names);
        fw.write("Customer List:\n");
        for ( int i = 0 ; i < names.size() ; i++ ) {
            for ( int j = 0 ; j < customerArrayList.size() ; j++ ) {
                if ( names.get(i).equals(customerArrayList.get(j).getCustomerName()) ) {
                    fw.write(customerArrayList.get(j).getCustomerID() + " " +
                            customerArrayList.get(j).getCustomerName() + " " +
                            customerArrayList.get(j).getCustomerSurname() + " " +
                            customerArrayList.get(j).getCustomerPhoneNumber() + " " +
                            customerArrayList.get(j).getCustomerAddress() + "\n");
                    break;
                }
            }
        }
        fw.flush();
    }

}
