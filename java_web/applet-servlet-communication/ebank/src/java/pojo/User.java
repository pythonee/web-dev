/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import java.util.Date;

/**
 *
 * @author pythonee
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int sex;
    private int userID;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public String getUsername() {
        return username;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
