package utils;

import bigList.myArrList;
import menus.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Singleton to keep track of view and some user relevant fields.
 */
public class ViewManager {
    private static ViewManager viewManager;
    private static view nextView;
    private myArrList<view> viewList;
    private boolean running;
    private Connection conn;
    private Scanner sc;
    private int account;
    private String currentUsername;

    private ViewManager(){
        viewList = new myArrList<view>();
        viewManager = this;
        running = true;
        sc = new Scanner(System.in);
        try {
            conn = ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewList.add(new introView(sc), 0);
        viewList.add(new mainMenu(sc), 1);
        viewList.add(new loginView(sc), 2);
        //viewList.add(new BankerView(sc), );
        viewList.add(new CustomerView(sc), 3);
    }

    public static ViewManager getViewManager(){
        if (viewManager == null){
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int number){
        account = number;
    }

    /**
     * sets up nextView before goToNextView() is called
     * @param newView is the name of the next view
     */
    public void navigate(String newView){
        view v;
        for (int i = 0; i < viewList.getCount(); i++){
            v = viewList.get(i);
            if (v.getViewName().equals(newView)){
                nextView = v;
            }
        }
    }

    /**
     * drives the program to the next view
     * @throws SQLException
     */
    public void goToNextView() throws SQLException{
        nextView.createView();
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean run){
        running = run;
    }

    public Connection getConn(){return conn;}

    /**
     * for keeping track of current user
     * @return
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

}
