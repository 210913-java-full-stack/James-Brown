package login;

/**
 * class to help the registration process
 */
public class Login {

    String username, password;
    int account_num;

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccount_num() {
        return account_num;
    }

    public Login(String user, String pass, int account){
        username = user;
        password = pass;
        account_num = account;
    }
}
