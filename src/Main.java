/*
*
* Execution of program starts in this class
* This class should being entered on command line
* Takes 3 argument from command line
*
* First file must be <inputfile>
* Second file must be <customerfile>
* Third file must be <orderfile>
*
* If arguments are not being entered in this order program gives an error and
* program terminates.
*
* Also, Main class calls the write methods.
*
*
* */

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException,NoSuchMethodException, InvocationTargetException {

        File inputFile = new File(args[0]); //<inputfile>
        File customerFile = new File(args[1]);  //<customerfile>
        File orderFile = new File(args[2]); //<orderfile>

        Operation operate = new Operation();
        operate.readInputFile(inputFile, customerFile, orderFile);
        operate.writeCustomerFile(customerFile);
        operate.writeOrderFile(orderFile);
    }
}
