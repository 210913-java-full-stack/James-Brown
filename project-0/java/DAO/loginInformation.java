package DAO;

import bigList.myArrList;

import java.io.IOException;
import java.sql.*;
import login.Login;
import utils.ConnectionManager;
import utils.ViewManager;


public class loginInformation{
    private Connection conn;
    private static loginInformation logInfo;
    private myArrList<Login> mal;
    private Statement st;
    private  ResultSet rs;
    private ViewManager vm;

    public loginInformation() throws SQLException, IOException{
        conn = ConnectionManager.getConnection();
        mal = new myArrList<Login>();
        populate();
        vm = ViewManager.getViewManager();
    }

//    public loginInformation getLoginInformation() throws SQLException, IOException{
//        if (logInfo == null){
//            logInfo = new loginInformation();
//        }
//        return logInfo;
//    }

    /**
     * For matching the current username to an account number for money transfer purposes
     *
     * @param username found using ViewManager getCurrentUsername
     * @return the account number relevant to the provided username
     */
    public int matchAcctNum(String username){
        int act = -1;

        for (int i = 0; i < mal.getCount(); i++){
            if (username.equalsIgnoreCase(mal.get(i).getUsername())){
                act = mal.get(i).getAccount_num();
            }
        }
        vm.setAccount(act);

        return act;
    }

    /**
     * Checks login information for validity
     * @param username
     * @param password
     * @return true if there is a match in the ArrayList. False otherwise.
     */
    public boolean checkLoginInfo(String username, String password){
        for (int i = 0; i < mal.getCount(); i++){
            if (mal.get(i).getUsername().compareToIgnoreCase(username) == 0 &&
            mal.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks login to see if any other users own it
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean isLoginTaken(String username) throws SQLException{
        for (int i = 0; i < mal.getCount(); i++){
            if (mal.get(i).getUsername().compareToIgnoreCase(username) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * fills arrayList with Login objects
     * that contain username, password and acct numbers
     * @throws SQLException
     */
    public void populate() throws SQLException{

        st = conn.createStatement();
        String sql = "SELECT username, password, account_num FROM customers c" +
                " JOIN account_location al on c.customer_id = al.customer_id;";
        rs = st.executeQuery(sql);

        while(rs.next()){
            mal.add(new Login(rs.getString("username"), rs.getString("password"), rs.getInt("account_num")));
        }

    }

//    public static void main(String[] args){
//        try {
//            loginInformation lf = loginInformation.getLoginInformation();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < mal.getCount(); i++){
//            System.out.println(mal.get(i).getUsername()+":"+mal.get(i).getPassword());
//        }
//    }
}
