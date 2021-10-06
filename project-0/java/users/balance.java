package users;

/**
 * class that represents the balance table in the DB
 */
public class balance {
    int account_num;
    double bal;

    public balance(){}

    public balance(int account_num, double bal){
        this.bal = bal;
        this.account_num = account_num;
    }

    public double getBalance() {
        return bal;
    }

    public void setBalance(int balance) {
        this.bal = balance;
    }

    public int getAccount_num() {
        return account_num;
    }

    public void setAccount_num(int account_num) {
        this.account_num = account_num;
    }
}
