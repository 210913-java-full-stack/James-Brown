package DAO;

import bigList.myArrList;
import users.Customer;
import utils.ViewManager;

import java.sql.*;

/**
 * This class is mainly for keeping the customers in a list
 */
public class customerDAO implements blueprintDAO<Customer> {

    private Connection conn;
    private final ViewManager vm;
    private PreparedStatement st;
    private myArrList<Customer> mAL;

    /**
     * initializes the ViewManager singleton, Connection, and myArrList
     * @param c
     * @throws SQLException
     */
    public customerDAO(Connection c) throws SQLException {
        vm = ViewManager.getViewManager();
        conn = vm.getConn();
        mAL = getAllItems();
    }

    @Override
    public void save(Customer customer) throws SQLException {}

    @Override
    public Customer getItem(int id) throws SQLException {
        return mAL.get(id);
    }

    @Override
    public void delete(int id) throws SQLException {
        mAL.remove(id);
    }


    /**
     * retrieves account name relevant to the current user
     * @return first name of current customer logged in
     * @throws SQLException
     */
    public String getAccountName() throws SQLException{
        String name;
        String sql = "SELECT first_name FROM customers c where c.username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, vm.getCurrentUsername());

        ResultSet rs = ps.executeQuery();
        rs.next();
        name = rs.getString("first_name");

        return name;
    }


    /**
     * fills arrayList with Customer objects that are present in the database
     * @return
     * @throws SQLException
     */
    @Override
    public myArrList<Customer> getAllItems() throws SQLException{
        myArrList<Customer> customerList = new myArrList<>();
        String sql = "SELECT * FROM customers";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            customerList.add(new Customer(rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("username"), rs.getString("password"),
                    rs.getInt("location_num"), rs.getInt("customer_id")));
        }


        return customerList;
    }

    protected void finalize() throws SQLException {
        conn.close();
    }

}
