package menus;

import DAO.loginInformation;
import DAO.introDAO;
import users.Customer;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.Console.*;

/**
 * This view is for users who select the Register option in main menu.
 * It will allow them to set up and account with a login/password.
 */
public class introView extends view{


    public introView(Scanner sc){
        super("introView", sc);
    }

    /**
     * View for customers who desire a new account. Takes personal information and inserts it into DB
     * @throws SQLException
     */
    @Override
    public void createView() throws SQLException {
        loginInformation logInfo = null;
        introDAO iDAO = new introDAO(vm.getConn());
        try {
            logInfo = new loginInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fName, lName, address, city, username, password;
        int zipCode;

        System.out.print("Welcome to the Bank register service! \n" +
                "Please enter your first name: ");


        fName = sc.nextLine();

        System.out.print("Please enter your last name: ");

        lName = sc.nextLine();

        System.out.print("Please enter your address: ");

        address = sc.nextLine();

        System.out.print("City: ");

        city = sc.nextLine();

        System.out.print("Zip Code: ");

        zipCode = sc.nextInt();
        sc.nextLine();

        System.out.print("Please choose a username: ");

        username = sc.nextLine();
        boolean isUserTaken = logInfo.isLoginTaken(username);

        while(isUserTaken){
            System.out.print("Username is taken. Submit a new one: ");
            username = sc.nextLine();
            isUserTaken = logInfo.isLoginTaken(username);
        }

        System.out.print("Please choose a password: ");

        password = sc.nextLine();

        iDAO.save(new Customer(fName, lName, username, password, address, city, zipCode));

        System.out.println("Thank you for registering.");

        vm.navigate("mainMenu");
    }

}
