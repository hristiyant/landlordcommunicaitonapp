package venkov.vladimir.thebeginning.models;

import java.io.Serializable;

public class User implements Serializable {

    private int id;

    private String firstName;

    private String middleName;

    private String lastName;

//    @Size(min = 10, max = 10, message = "Phone number should be in format 0888123456 - 10 digits long")
    private String phoneNumber;

    private Boolean isLandlord;

    private BankAccount bankAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getLandlord() {
        return isLandlord;
    }

    public void setLandlord(Boolean landlord) {
        isLandlord = landlord;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
