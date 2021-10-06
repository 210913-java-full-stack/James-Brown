package users;

public class Customer {
    String fName, lName, username, password, address, city;
    int zip_code, customer_id, location_id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public Customer(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getLocation_id() {
        return location_id;
    }


    public Customer(String first, String last, String user, String pw){
        fName = first;
        lName = last;
        username = user;
        password = pw;
    }

    public Customer(String first, String last, String user, String pw, int loc_num, int c_id){
        fName = first;
        lName = last;
        location_id = loc_num;
        customer_id = c_id;
        username = user;
        password = pw;
    }
    public Customer(String first, String last, String user, String pw,
                    int c_id, String addy, String c, int loc_id, int zip){
        fName = first;
        lName = last;
        customer_id = c_id;
        location_id = loc_id;
        username = user;
        password = pw;
        address = addy;
        city = c;
        zip_code = zip;
    }

    public Customer(String first, String last, String user, String pw, String addy, String c, int zip){
        fName = first;
        lName = last;
        username = user;
        password = pw;
        address = addy;
        city = c;
        zip_code = zip;
    }
}
