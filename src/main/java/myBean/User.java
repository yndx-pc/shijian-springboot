package myBean;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
//    private void User(String admin, String password){
//        this.admin = admin;
//        this.password = password;
//    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
