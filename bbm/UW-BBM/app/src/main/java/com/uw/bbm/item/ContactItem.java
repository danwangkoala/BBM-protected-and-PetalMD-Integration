package com.uw.bbm.item;

/**
 * Created by jiy on 4/4/16.
 */
public class ContactItem {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String group = "";
    private String timePeriod = "";
    private String contactNumber = "";

    public ContactItem(String firstName, String lastName, String email, String group, String timePeriod, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group = group;
        this.timePeriod = timePeriod;
        this.contactNumber = contactNumber;
    }

    public ContactItem() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName(){
        return firstName + " " + lastName;
    }
}
