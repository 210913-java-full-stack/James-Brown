package DAO;

import bigList.myArrList;
import users.Customer;

import java.sql.*;

/**
 * This class is for processing the login information in one place
 * so the data does not get inputted out of order
 */
public class introDAO implements blueprintDAO<Customer> {

    private ResultSet rs;
    private PreparedStatement st;
    private Connection con;
    private int currentAccNum, currentCID;

    /**
     * constructor for the introDAO
     * calls updateNumbers and sets the connection up
     * @param c
     */
    public introDAO(Connection c){
        con = c;
        try {
            updateNumbers();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * sets fields that keep track of what
     * the next account number/customer id needs to be
     * @throws SQLException
     */
    private void updateNumbers() throws SQLException{
        String sql = "SELECT MAX(account_num), MAX(customer_id) FROM account_location";
        Statement statement = con.createStatement();
        rs = statement.executeQuery(sql);

        while(rs.next()) {
            currentAccNum = rs.getInt(1);
            currentCID = rs.getInt(2);
        }
    }

    /**
     * Inserts new users into the database
     * @param c Customer Object created by the user after inputting personl information
     * @throws SQLException
     */
    @Override
    public void save(Customer c) throws SQLException {

        String sql = "INSERT INTO account_location (account_num, customer_id) VALUES (?, ?)";
        st = con.prepareStatement(sql);
        st.setInt(1, currentAccNum+1);
        st.setInt(2, currentCID+1);
        st.executeUpdate();


        sql = "INSERT INTO location (address, city, zip_code) VALUES (?, ?, ?);";
        st = con.prepareStatement(sql);
        st.setString(1, c.getAddress());
        st.setString(2, c.getCity());
        st.setInt(3, c.getZip_code());
        st.executeUpdate();

        sql = "INSERT INTO customers (first_name, last_name, " +
                "customer_id, username, password) " +
                "VALUES (?, ?, ?, ?, ?)";
        st = con.prepareStatement(sql);
        st.setString(1, c.getfName());
        st.setString(2, c.getlName());
        st.setInt(3, currentCID+1);
        st.setString(4, c.getUsername());
        st.setString(5, c.getPassword());
        st.executeUpdate();

        st = con.prepareStatement("INSERT INTO balance (balance, account_num)" +
                "VALUES (?, ?)");
        st.setDouble(1, 0.00);
        st.setInt(2, currentAccNum+1);
        st.executeUpdate();

    }

    @Override
    public Customer getItem(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public myArrList getAllItems() {
        return null;
    }

//    public static void main(String[] args){
//        introDAO iDAO = null;
//        try {
//            iDAO = new introDAO(ConnectionManager.getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            iDAO.save(new Customer("Goofy", "The Goof", "charArray", "passgoof",
//                    "Disney World", "Orlando", 28241));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

}
