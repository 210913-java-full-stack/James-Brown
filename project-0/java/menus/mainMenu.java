package menus;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Where the program will always begin
 */
public class mainMenu extends view{

    public mainMenu(Scanner scanner) {
        super("mainMenu", scanner);
    }

    /**
     * Introductory view for all users
     * @throws SQLException
     */
    @Override
    public void createView() throws SQLException {
        int choice;
        System.out.println("Hello, welcome to Bank of Brown!\n" +
                "0: Register new User\n" +
                "1: Login\n" +
                "Q: Quit");
        switch (sc.nextLine()) {
            case "0":
                vm.navigate("introView");
                return;
            case "1":
                vm.navigate("loginView");
                return;
            case "Q":
            case "q":
                vm.setRunning(false);
                return;
        }
    }
}
