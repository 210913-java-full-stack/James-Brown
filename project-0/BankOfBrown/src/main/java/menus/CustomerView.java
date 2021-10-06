package menus;

import DAO.accountDAO;
import DAO.customerDAO;
import bigList.myArrList;
import users.Customer;
import utils.ConnectionManager;

import java.io.IOException;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Scanner;

public class CustomerView extends view{

    private customerDAO cDAO;
    private myArrList<Customer> mAL;
    private accountDAO accDAO;
    private final NumberFormat nf;


    /**
     * constructor for CustomerView. Sets up NumberFormat for printing in currency format
     * @param scanner
     */
    public CustomerView(Scanner scanner) {
        super("CustomerView", scanner);

        nf = NumberFormat.getCurrencyInstance();

        try {
            cDAO = new customerDAO(ConnectionManager.getConnection());
            accDAO = new accountDAO(ConnectionManager.getConnection());
            mAL = cDAO.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Options for users who have created an account and logged in
     * @throws SQLException
     */
    @Override
    public void createView() throws SQLException {
        String s;
        double d;

        System.out.println("\nWelcome to your account, " + cDAO.getAccountName());

        System.out.println("1: View account balance\n2: Deposit funds\n3: Withdraw funds\n4: Logout");
        switch(sc.nextLine().trim()){
            case "1":
                System.out.printf("%d: %s",
                        accDAO.getBal().getAccount_num(),
                        nf.format(accDAO.getBal().getBalance()));
                break;
            case "2":
                System.out.print("Please enter your deposit amount.\nCurrent Balance: "
                        + nf.format(accDAO.getBal().getBalance()) + "\n");
                s = sc.nextLine();
                d = Double.parseDouble(s);

                while (d < 0){
                    System.out.print("Negative submissions are not valid" +
                        "\nPlease submit your deposit amount: ");
                    s = sc.nextLine();
                    d = Double.parseDouble(s);
                }

                System.out.print(nf.format(d) + " will be deposited into account: ");
                accDAO.deposit(d);
                break;
            case "3":
                System.out.print("Please enter your withdrawal amount.\nCurrent Balance: "
                        + nf.format(accDAO.getBal().getBalance()) + "\n");

                s = sc.nextLine();
                d = Double.parseDouble(s);

                while(d < 0) {
                    System.out.print("Please enter positive values only");
                    s = sc.nextLine();
                    d = Double.parseDouble(s);
                }

                while (accDAO.isNegative(d)){
                    System.out.print("Your current balance is: "
                            + nf.format(accDAO.getBal().getBalance())
                            + "\nPlease enter a smaller amount " +
                            "or Q to go back to Your menu: ");

                    s = sc.nextLine();

                    if (s.trim().equals("q") || s.trim().equals("Q")) {break;}
                    else {d = Double.parseDouble(s);}
                }
                accDAO.withdraw(d);
                break;
            case "4":
                vm.navigate("mainMenu");
                break;
            default:
                System.out.println("Please submit a valid option");
        }
    }
}
