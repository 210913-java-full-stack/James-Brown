package DAO;

import bigList.myArrList;
import com.sun.corba.se.pept.transport.ConnectionCache;
import users.Banker;
import users.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class bankerDAO implements blueprintDAO<Banker>{
    Connection conn;

    public bankerDAO(Connection c){
        conn = c;
    }

    @Override
    public void save(Banker banker) throws SQLException {

    }

    @Override
    public Banker getItem(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public myArrList<Banker> getAllItems() throws SQLException{
//        myArrList<Banker> bankerList = new myArrList<>();
//        String sql = "SELECT * FROM customers";
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//
//        while(rs.next()){
//            bankerList.add(new Banker("r", "l"));
//        }
//
//
//        return bankerList;
        return null;
    }

    public void finalize() throws SQLException {
        conn.close();
    }
}
