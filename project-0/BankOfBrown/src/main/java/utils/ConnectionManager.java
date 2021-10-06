package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton to keep track of connection to DB
 */
public class ConnectionManager {
    private static Connection c;

    private ConnectionManager(){}

    //singleton for db connection
    public static Connection getConnection() throws SQLException, IOException {
        if(c == null) {
            Properties props = new Properties();
            FileReader connectionProperties = new FileReader("src/main/resources/connections.properties");
            props.load(connectionProperties);

            //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
            String connString = "jdbc:mariadb://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("databaseName") + "?user=" +
                    props.getProperty("user") + "&password=" +
                    props.getProperty("password");


            c = DriverManager.getConnection(connString);
        }
        return c;
    }
}
