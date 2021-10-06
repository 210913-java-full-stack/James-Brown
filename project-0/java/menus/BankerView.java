package menus;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *  View for admin
 */
public class BankerView extends view{

    public BankerView(Scanner scanner) {
        super("BankerView", scanner);
    }

    @Override
    public void createView() throws SQLException {
        System.out.print("");
    }
}
