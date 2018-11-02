package venkov.vladimir.thebeginning.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

public class Accommodation implements Serializable {

    private int id;

//    private Location location;

    private String address;

    private User tenant;

    private User landlord;

    private double price;

    private double longitude;

    private double latitude;

    private Timestamp dueDate;

    private Timestamp dueLastSentDate;

    public Accommodation() {
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

//    public Location getLocation() {
//        return location;
//    }

    public String getAddress() {
        return address;
    }

    public User getTenant() {
        return tenant;
    }

    public User getLandlord() {
        return landlord;
    }

    public double getPrice() {
        return price;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public Timestamp getDueLastSentDate() {
        return dueLastSentDate;
    }

    public void setDueLastSentDate(Timestamp dueLastSentDate) {
        this.dueLastSentDate = dueLastSentDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
