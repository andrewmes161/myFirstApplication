package com.example.module3assignment.myfiles;

// Houses all info about User

public class User {

    // Variables //

    private int id;
    private String userName;
    private String userPassword;
    itemList myItemList; // Shouldn't be any getters or setters since this is a referenced
                         // object.

    // Constructors //
    public User() {
    }

    public User(int id, String userName, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        // myItemList = new itemList(); // No input because it's first created
    }

    // toString is necessary for printing the contents of a class


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                //", myItemList=" + myItemList +
                '}';
    }

    // Getters & Setters //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
