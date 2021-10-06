package DAO;

import bigList.myArrList;
import users.balance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class for processing balance related information.
 */
public class balanceDAO implements blueprintDAO<balance> {

    private Connection conn;
    private myArrList<balance> balanceList;

    /**
     * initializes Connection and the balance list
     * @param c
     * @throws SQLException
     */
    public balanceDAO(Connection c) throws SQLException {
        conn = c;
        balanceList = getAllItems();
    }

    /**
     *
     * @param b
     * @throws SQLException
     */
    @Override
    public void save(balance b) throws SQLException {}

    /**
     * Retrieves balance and account number for current user
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public balance getItem(int id) throws SQLException {
        return null;
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {

    }

    /**
     * populates ArrayList for account numbers and balances
     * @return
     * @throws SQLException
     */
    @Override
    public myArrList getAllItems() throws SQLException {
        myArrList<balance> balanceList = new myArrList<>();
        String sql = "SELECT * FROM balance";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            balanceList.add(new balance(rs.getInt("account_num"), rs.getDouble("balance")));
        }


        return balanceList;
    }

//    public static void main(String[] args){
//        balanceDAO bDAO = null;
//        myArrList<balance> mAL = null;
//        String str = null;
//        NumberFormat nf = NumberFormat.getCurrencyInstance();
//        try {
//            bDAO = new balanceDAO(ConnectionManager.getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            mAL = bDAO.getAllItems();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        for(int i = 0; i < mAL.getCount(); i++){
//            System.out.printf("%d:%s\n",mAL.get(i).getAccount_num(), nf.format(mAL.get(i).getBalance()));
//        }
//
//    }

}
