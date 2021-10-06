package menus;

import utils.ViewManager;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * blueprint class for the views
 */
public abstract class view {
    protected Scanner sc;
    protected String viewName;
    protected ViewManager vm;

    public view(String vn, Scanner scanner){
        sc = scanner;
        viewName = vn;
        vm = ViewManager.getViewManager();
    }

    public String getViewName(){
        return viewName;
    }

    /**
     * provides account details depending on the view
     * @throws SQLException
     */
    public abstract void createView() throws SQLException;
}
