package com.example.eazee;

public final class UserInformation {

    private String user_id;
    private String user_name;
    private String user_email;

    private final static UserInformation INSTANCE = new UserInformation();

    private UserInformation() {}

    public static UserInformation getInstance() {
        return INSTANCE;
    }

    public void setUser(String u_id,String u_n, String u_e) {
        this.user_id = u_id;
        this.user_name = u_n;
        this.user_email = u_e;
    }

    public String getUserID() {
        return this.user_id;
    }

    public String getUserName() {
        return this.user_name;
    }

    public String getUserEmail() {
        return this.user_email;
    }
}
