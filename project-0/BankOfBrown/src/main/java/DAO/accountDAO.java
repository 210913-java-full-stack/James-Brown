package DAO;

import bigList.myArrList;
import users.balance;
import utils.ViewManager;

import java.sql.*;

/**
 * DAO for retrieving/checking balance information
 */
public class accountDAO implements blueprintDAO<balance> {

    Connection c;
    ResultSet rs;
    PreparedStatement st;
    myArrList<balance> balList;
    ViewManager vm;

    public accountDAO(Connection con)throws SQLException{
        c = con;
        balList = getAllItems();
        vm = ViewManager.getViewManager();
    }

    //Saves to DB
    public void save(balance a) throws SQLException{};

    /**
     * Method for checking if potential withdrawal could put the user in the negative
     *
     * @param amt the amount of money
     *
     * @return Returns false if transaction is safe
     */
    public boolean isNegative(double amt){
        balance bal = null;
        try {
            bal = getBal();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(bal.getBalance() > amt){
            return false;
        } else {return true;}
    }

    //retrieves item from DB
    public balance getItem(int id) throws SQLException{
        return null;
    }

    //removes item from DB
    public void delete(int id) throws SQLException{

    }


    /**
     * returns a balance Object for the current user logged in
     * @return
     * @throws SQLException
     */
    public balance getBal() throws SQLException{
        String sql = "SELECT b.account_num, balance " +
                "FROM balance b " +
                "JOIN account_location al on al.account_num = b.account_num " +
                "JOIN customers c on c.customer_id = al.customer_id " +
                "WHERE username = ?";
        st = vm.getConn().prepareStatement(sql);
        st.setString(1,vm.getCurrentUsername());
        rs = st.executeQuery();

        rs.next();

        return new balance(rs.getInt(1), rs.getDouble(2));
    }

    /**
     * gathers all of the available balances and
     * puts them into an arrayList
     * @return
     * @throws SQLException
     */
    public myArrList<balance> getAllItems() throws SQLException{
        myArrList<balance> balanceList = new myArrList<>();
        String sql = "SELECT * FROM balance";
        Statement st = c.createStatement();
        rs = st.executeQuery(sql);

        while(rs.next()){
            balanceList.add(new balance(rs.getInt("account_num"), rs.getDouble("balance")));
        }

        return balanceList;
    }

    /**
     * for closing out connections once they are no longer required
     * @throws SQLException
     */
    public void finalize() throws SQLException {
        c.close();
    }

    public static void main(String[] args) throws SQLException {
        ViewManager vm = ViewManager.getViewManager();
        vm.setCurrentUsername("userjames");
        accountDAO aD = new accountDAO(vm.getConn());

        balance bal = aD.getBal();
        System.out.println(bal.getBalance());
    }

    /**
     * deposits d money into user account
     * @param d checked for negative values
     * @throws SQLException
     */
    public void deposit(double d) throws SQLException {
        System.out.println(vm.getAccount());
        String sql = "UPDATE balance b " +
                "SET balance = balance + ? " +
                "WHERE account_num = ?";

        st = c.prepareStatement(sql);
        st.setDouble(1, d);
        st.setInt(2, vm.getAccount());
        st.executeQuery();
    }

    public void withdraw(double d) throws SQLException{
        String sql = "UPDATE balance b " +
                "SET balance = balance - ? "+
                "WHERE account_num = ?";

        st = c.prepareStatement(sql);
        st.setDouble(1, d);
        st.setInt(2, vm.getAccount());
        st.executeQuery();
    }


}
