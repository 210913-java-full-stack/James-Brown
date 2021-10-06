package menus;

import DAO.loginInformation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * View class for users signing in
 */
public class loginView extends view{
    Scanner sc = new Scanner(System.in);

    public loginView(Scanner sc){
        super("loginView", sc);
    }

    /**
     * User Login process
     * @throws SQLException
     */
    @Override
    public void createView() throws SQLException {
        loginInformation logInfo = null;
        try {
            logInfo = new loginInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username, password;
        System.out.print("Enter your username: ");
        username = sc.nextLine();
        System.out.print("Enter your password: ");
        password = sc.nextLine();

        if(logInfo.checkLoginInfo(username, password)){
            vm.setCurrentUsername(username);
            logInfo.matchAcctNum(username);
            vm.navigate("CustomerView");
        }else {
            System.out.println("Invalid Credentials");
            vm.navigate("mainMenu");
        }
    }
}
