package DAO;

import bigList.myArrList;
import java.sql.Connection;
import java.sql.SQLException;

public interface blueprintDAO<T> {
    //Saves to DB
    public void save(T t) throws SQLException;

    //retrieves item from DB
    public T getItem(int id) throws SQLException;

    //removes item from DB
    public void delete(int id) throws SQLException;


    public myArrList<T> getAllItems() throws SQLException;


}
