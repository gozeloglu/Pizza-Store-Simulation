/*
*
*  This class make operations on input file. These files are are input.txt, <customerfile> and
*  <orderfile>
*
*  Operation class reads 3 of file line by and calls the related methods from CustomerDAOImp and
*  OrderDAOImp classes.
*
*  Also, writing(updating) file operations are being done in this class.
*
*
*  */




import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.lang.System.exit;

public class Operation {
    /* customerDAO and orderDAOImp object are using for calling related methods from their classes.*/
    CustomerDAOImp customerDAO = new CustomerDAOImp();
    OrderDAOImp orderDAOImp = new OrderDAOImp();

    FileWriter fw = null;
    FileWriter fwc = null;
    FileWriter fwo = null;


    /* This method reads input file line by line and calls the related methods from CustomerDAOImp nad OrderDAOImp classes.*/
    public void readInputFile(File inputFile, File customerFile, File orderFile) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        fw = new FileWriter("output.txt"); //Writes the output after operations.

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            readCustomerFile(customerFile);
            readOrderFile(orderFile);
            String str;

            try {

                while ( (str = br.readLine()) != null ) {


                    //////////////////////////////////ADD DRINK \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    if (str.substring(0,8).equalsIgnoreCase("AddDrink")) {

                        int x = 0;
                        String[] drinkID = str.split(" ");

                        orderDAOImp.addDrink(drinkID[1], fw, true);
                    }

                    ///////////////////////////////////PAY CHECK\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if (str.substring(0, 8).equalsIgnoreCase("PayCheck")) {
                        String[] payCheckArr = str.split(" ");

                        orderDAOImp.payCheck(payCheckArr[1], fw);

                    }

                    ///////////////////////////////////////////CREATE ORDER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if ( str.substring(0,11).equalsIgnoreCase("CreateOrder") ) {
                        int x = 0;
                        String[] createOrderArr = str.split(" ");

                        orderDAOImp.createOrder(createOrderArr[1], createOrderArr[2], fw, true);
                    }

                    /////////////////////////////////////////ADD CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if (str.substring(0, 11).equalsIgnoreCase("AddCustomer")) {

                        int x = 0;

                        String[] customerArray = str.split(" ", 6);

                        customerDAO.addCustomer(customerArray[1], customerArray[2], customerArray[3], customerArray[4],customerArray[5], fw, true);
                    }

                    ////////////////////////////////////LIST CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if (str.substring(0, 13).equalsIgnoreCase("ListCustomers")) {

                        customerDAO.customerList(fw);
                    }

                    ///////////////////////////////////REMOVE ORDER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if ( str.substring(0, 11).equalsIgnoreCase("RemoveOrder") ) {
                        String[] line = str.split(" ");
                        orderDAOImp.removeOrderID(line[1], fw);
                    }

                    /////////////////////////////////////REMOVE CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if ( str.substring(0, 14).equalsIgnoreCase("RemoveCustomer") ) {
                        String[] idCustomer = str.split(" ");

                        customerDAO.removeByID(idCustomer[1], fw, orderDAOImp);
                    }

                    ///////////////////////////////////ADD PIZZA\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if ( str.substring(0, 8).equalsIgnoreCase("AddPizza") ) {
                        int x = 0;
                        String idOrder = "";

                        int toppingNum = makeControlOrderNum(str);

                        if (toppingNum == 0) {      //If the user tries to give an order which has more than 3 toppings, this error messages writes out the output.
                            fw.write("You cannot give order more than included 3 toppings!\n");

                        } else {
                            while (!(str.substring(x, x + 1).equals(" "))) {
                                x++;
                            }
                            x++;
                            while (!(str.substring(x, x + 1).equals(" "))) {
                                idOrder += str.substring(x, x + 1);
                                x++;
                            }
                            x++;
                            String[] toppingArray = new String[toppingNum];
                            toppingArray = str.substring(x, str.length()).split(" ");

                            if ( toppingNum == 1) {     //Basic Pizza
                                try {

                                    Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(toppingArray[0]);

                                    Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();

                                    Pizza newPizza = pizzaConstructor.newInstance();

                                    orderDAOImp.addPizza(newPizza, idOrder, toppingArray[0], fw);
                                } catch (ClassNotFoundException e) {
                                    fw.write("WARNING! There is no such thing topping or pizza!\n");
                                }


                            }
                            else if ( toppingNum == 2 ) {       //Pizza + 1 topping
                                try {

                                    Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(toppingArray[0]);
                                    Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(toppingArray[1]);

                                    Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                    Constructor<Pizza> toppingConstructor = toppingOne.getConstructor(Pizza.class);

                                    Pizza newPizza = toppingConstructor.newInstance(pizzaConstructor.newInstance());


                                    orderDAOImp.addPizza(newPizza, idOrder, toppingArray[0], fw);
                                } catch (ClassNotFoundException e) {
                                    fw.write("WARNING! There is no such thing topping or pizza!\n");
                                }
                            }
                            else if ( toppingNum == 3 ) {       //Pizza + 2 topping
                                try {
                                    Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(toppingArray[0]);
                                    Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(toppingArray[1]);
                                    Class<Pizza> toppingTwo = (Class<Pizza>) Class.forName(toppingArray[2]);

                                    Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                    Constructor<Pizza> toppingOneConstructor = toppingOne.getConstructor(Pizza.class);
                                    Constructor<Pizza> toppingTwoConstructor = toppingTwo.getConstructor(Pizza.class);

                                    Pizza newPizza = toppingTwoConstructor.newInstance(toppingOneConstructor.newInstance(pizzaConstructor.newInstance()));

                                    orderDAOImp.addPizza(newPizza, idOrder, toppingArray[0], fw);
                                } catch (ClassNotFoundException e) {
                                    fw.write("WARNING! There is no such thing topping or pizza!\n");
                                }
                            }
                            else if ( toppingNum == 4 ) {   //Pizza + 3 topping
                                try {

                                    Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(toppingArray[0]);
                                    Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(toppingArray[1]);
                                    Class<Pizza> toppingTwo = (Class<Pizza>) Class.forName(toppingArray[2]);
                                    Class<Pizza> toppingThree = (Class<Pizza>) Class.forName(toppingArray[3]);

                                    Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                    Constructor<Pizza> toppingOneConstructor = toppingOne.getConstructor(Pizza.class);
                                    Constructor<Pizza> toppingTwoConstructor = toppingTwo.getConstructor(Pizza.class);
                                    Constructor<Pizza> toppingThreeConstructor = toppingThree.getConstructor(Pizza.class);

                                    Pizza newPizza = toppingThreeConstructor.newInstance(toppingTwoConstructor.newInstance(toppingOneConstructor.newInstance(pizzaConstructor.newInstance())));

                                    orderDAOImp.addPizza(newPizza, idOrder, toppingArray[0], fw);
                                } catch (ClassNotFoundException e) {
                                    fw.write("WARNING! There is no such thing topping or pizza!\n");
                                }
                            }
                        }

                    } else {
                        fw.write("Any other situation\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("IOException handled!");
                exit(-1);
            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File could not found!");
            exit(-1);
        }

        fw.close();
    }

    /*This method make controls of number of toppings. Returns how many toppings are there.
    * If there are more than 3 toppings, returns 0*/
    public int makeControlOrderNum(String str) {
        int counter = 0;

        for ( char c : str.toCharArray() ) {    //String converts to array with using toCharArray()
            if ( c == '\n' ) {
                break;
            } else {
                if ( c == ' ') {
                    counter++;
                }
            }
        }
        if ( counter == 2 ) { //Just basic pizza
            return 1;
        }
        else if ( counter == 3 ) {  //Pizza + 1 topping
            return 2;
        }
        else if ( counter == 4 ) {  //Pizza + 2 toppings
            return 3;
        }
        else if ( counter == 5 ) {  //Pizza + 3 toppings
            return 4;
        }
        else{   //There are more than 3 topping order
            return 0;
        }

    }


    /* Reverse the given string which as a parameter. */
    public String reversePizza(String str) {
        String[] pizzaNameArr = str.split(" ");
        int arrLength = pizzaNameArr.length;

        /* Swaps the array's elements. */
        for ( int i = 0 ; i < arrLength/2 ; i++ ) {
            String tmp = pizzaNameArr[i];
            pizzaNameArr[i] = pizzaNameArr[arrLength-1];
            pizzaNameArr[arrLength-1] = tmp;
        }

        String result = "";
        int j = 0;

        /* This loop append a string to empty string */
        while ( j < arrLength ) {
            result += pizzaNameArr[j];
            result += " ";
            j++;
        }

        return result;
    }


    /*Reading customer file is being done in this method.*/
    public void readCustomerFile(File customerFile) {
        try {
            BufferedReader brc = new BufferedReader(new FileReader(customerFile));
            String str;

            try {
                while ( (str = brc.readLine()) != null ) {
                    String[] customerLine = str.split(" ", 6);

                    customerDAO.addCustomer(customerLine[0], customerLine[1], customerLine[2],
                            customerLine[3], customerLine[5], fwc, false);
                }

                brc.close();
            }catch (IOException e) {
                System.out.println("IOException handled!");
                exit(-1);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File could not found!");
            exit(-1);
        }
    }

    /*Reading order file is being in this method.*/
    public void readOrderFile(File orderFile) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        try {
            BufferedReader bro = new BufferedReader(new FileReader(orderFile));
            String str;

            List<Customer> customerArrayList = customerDAO.getCustomerArrayList();

            try {
                int x = 0;
                String customerID = "";
                String id = "";
                while ( (str = bro.readLine()) != null ) {
                    if ( (str.substring(0, 5)).equalsIgnoreCase("Order") ) {
                        String[] orderArr = str.split(" ");
                        customerID = orderArr[2];
                        for ( int i = 0 ; i < customerArrayList.size() ; i++ ) {
                            if ( (customerArrayList.get(i).getCustomerID()).equals(orderArr[2]) ) {

                                orderDAOImp.createOrder(id,customerArrayList.get(i).getCustomerID(), fw, false);
                                id = orderArr[1];
                                break;
                            }
                        }
                    }
                    else if ( str.equalsIgnoreCase("SoftDrink")) {

                        orderDAOImp.addDrink(id, fw, false);
                    } else {

                        List<String> pizzaAndToppings = new ArrayList<>();

                        String[] tmpStr = str.split(" ");
                        int y = 0;
                        for ( String s : tmpStr ) {
                            pizzaAndToppings.add(s);

                            y++;
                        }

                        if ( pizzaAndToppings.size() == 1 ) {
                            try {

                                Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(pizzaAndToppings.get(0));

                                Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();

                                Pizza newPizza = pizzaConstructor.newInstance();

                                orderDAOImp.addPizzaFromFile(newPizza, id, customerID);
                            } catch (ClassNotFoundException e ) {
                                continue;
                            }

                        }
                        else if ( pizzaAndToppings.size() == 2 ) {
                            try {

                                Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(pizzaAndToppings.get(0));
                                Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(pizzaAndToppings.get(1));

                                Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                Constructor<Pizza> toppingOneConstructor = toppingOne.getConstructor(Pizza.class);

                                Pizza newPizza = toppingOneConstructor.newInstance(pizzaConstructor.newInstance());


                                orderDAOImp.addPizzaFromFile(newPizza, id, customerID);
                            } catch (ClassNotFoundException e ) {
                                continue;
                            }
                        }
                        else if ( pizzaAndToppings.size() == 3 ) {
                            try {

                                Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(pizzaAndToppings.get(0));
                                Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(pizzaAndToppings.get(1));
                                Class<Pizza> toppingTwo = (Class<Pizza>) Class.forName(pizzaAndToppings.get(2));

                                Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                Constructor<Pizza> toppingOneConstructor = toppingOne.getConstructor(Pizza.class);
                                Constructor<Pizza> toppingTwoConstructor = toppingTwo.getConstructor(Pizza.class);

                                Pizza newPizza = toppingTwoConstructor.newInstance(toppingOneConstructor.newInstance(pizzaConstructor.newInstance()));

                                orderDAOImp.addPizzaFromFile(newPizza, id, customerID);
                            } catch (ClassNotFoundException e ) {
                                continue;
                            }
                        }
                        else if ( pizzaAndToppings.size() == 4 ) {
                            try {
                                Class<Pizza> pizzaName = (Class<Pizza>) Class.forName(pizzaAndToppings.get(0));
                                Class<Pizza> toppingOne = (Class<Pizza>) Class.forName(pizzaAndToppings.get(1));
                                Class<Pizza> toppingTwo = (Class<Pizza>) Class.forName(pizzaAndToppings.get(2));
                                Class<Pizza> toppingThree = (Class<Pizza>) Class.forName(pizzaAndToppings.get(3));

                                Constructor<Pizza> pizzaConstructor = pizzaName.getConstructor();
                                Constructor<Pizza> toppingOneConstructor = toppingOne.getConstructor(Pizza.class);
                                Constructor<Pizza> toppingTwoConstructor = toppingTwo.getConstructor(Pizza.class);
                                Constructor<Pizza> toppingThreeConstructor = toppingThree.getConstructor(Pizza.class);

                                Pizza newPizza = toppingThreeConstructor.newInstance(toppingTwoConstructor.newInstance(toppingOneConstructor.newInstance(pizzaConstructor.newInstance())));


                                orderDAOImp.addPizzaFromFile(newPizza, id, customerID);
                            } catch (ClassNotFoundException e ) {
                                continue;
                            }
                        } else {
                            fw.write("You cannot give order more than included 3 toppings!");

                        }
                    }
                }
                bro.close();
            } catch (IOException e) {
                System.out.println("IOException handled!");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not found!");
            exit(-1);
        }
    }

    /*Writing customer file operations is being done in this method.*/
    public void writeCustomerFile(File customerFile) throws IOException {
        fwc = new FileWriter(customerFile, false);
        List<Integer> idArrList = new ArrayList<>();

        List<Customer> customerArrayList = customerDAO.getCustomerArrayList();


        for ( int i = 0 ; i < customerArrayList.size() ; i++ ) {
            idArrList.add(Integer.parseInt(customerArrayList.get(i).getCustomerID()));
        }

        Collections.sort(idArrList);

        for ( int j = 0 ; j < idArrList.size() ; j++) {
            String tmp = String.valueOf(idArrList.get(j));

            for (int i = 0; i < customerArrayList.size(); i++) {
                if ( tmp.equals(customerArrayList.get(i).getCustomerID()) ) {

                    fwc.write(customerArrayList.get(i).getCustomerID() + " " +
                            customerArrayList.get(i).getCustomerName() + " " +
                            customerArrayList.get(i).getCustomerSurname() + " " +
                            customerArrayList.get(i).getCustomerPhoneNumber() + " " + "Address:" +
                            customerArrayList.get(i).getCustomerAddress() + "\n");
                }
            }
        }
        fwc.close();
    }

    /*Writing order file operations is being done in this method.*/
    public void writeOrderFile(File orderFile) throws IOException {

        fwo = new FileWriter(orderFile, false);
        Set<Integer> idSet = new HashSet<>();

        List<Order> orderArrayList = orderDAOImp.getOrderArrayList();

        for ( int i = 0 ; i < orderArrayList.size() ; i++ ) {
            if ( orderArrayList.get(i).getOrderID() != "" ) {
                idSet.add(Integer.parseInt(orderArrayList.get(i).getOrderID()));
            }
        }


        List<Integer> idArrList = new ArrayList<>();
        idArrList.addAll(idSet);
        Collections.sort(idArrList);

        int counter;
        for ( int i = 0 ; i < idArrList.size() ; i++ ) {
            counter = 0;
            String idTmp = String.valueOf(idArrList.get(i));
            int tmp = 0;
            fwo.write("Order: " + idTmp);
            for ( int j = 0 ; j < orderArrayList.size() ; j++ ) {
                if ( idTmp.equals(orderArrayList.get(j).getOrderID()) ) {
                    if ( tmp == 0 ) {
                        fwo.write(" " + orderArrayList.get(j).getCustomerID() + "\n");
                        tmp++;
                    }
                    if ( (orderArrayList.get(j).getOrderPizza() != null) ) {
                        fwo.write(reversePizza(orderArrayList.get(j).getOrderPizza().getDescription()) + "\n");
                    }

                    if ( orderArrayList.get(j).getOrderSoftDrink() != 0 ) {
                        counter++;
                    }
                }
            }
            if ( counter > 0 ) {
                fwo.write("SoftDrink\n");
            }
        }
        fwo.close();
    }
}