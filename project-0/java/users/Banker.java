package users;

public class Banker {
    int banker_id;
    String fName, lName, username, password;


    public Banker(){}

    public Banker(String f, String l){
        fName = f;
        lName = l;
        username = "admin";
        password = "BornToBank!";
    }

    public int getBanker_id() {
        return banker_id;
    }

    public void setBanker_id(int banker_id) {
        this.banker_id = banker_id;
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

    public Banker (String f, String l, int id){
        banker_id = id;
        fName = f;
        lName = l;
        username = "admin";
        password = "BornToBank!";
    }


}
