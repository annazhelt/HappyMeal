package happymeal.entity;

public class User {
    private String user_name;
    private String password;
    private boolean isAdminFlag;

    public User(){}

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminFlag() {
        return isAdminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        isAdminFlag = adminFlag;
    }
}

