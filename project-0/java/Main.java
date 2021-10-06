import bigList.myArrList;
import utils.*;
import users.balance;

import java.io.IOException;
import java.sql.*;


/**
 * Main class. Hands off control to ViewManager
 */
public class Main {
    public static void main(String[] args){
        ViewManager vm = ViewManager.getViewManager();
        vm.navigate("mainMenu");
       try {
           while(vm.isRunning()){
               vm.goToNextView();
           }

           vm.getConn().close();

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
}
