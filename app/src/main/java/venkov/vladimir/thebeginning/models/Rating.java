package venkov.vladimir.thebeginning.models;

public class Rating {

    private int id;

    private double rating;

    private int ratedUserId;

    private int sourceUserId;

    public Rating() {
    }

    public Rating(int ratedUser, int sourceUser, double rating) {
        this.rating = rating;
        this.ratedUserId = ratedUser;
        this.sourceUserId = sourceUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatedUser() {
        return ratedUserId;
    }

    public void setRatedUser(int ratedUserId) {
        this.ratedUserId = ratedUserId;
    }

    public int getSourceUser() {
        return sourceUserId;
    }

    public void setSourceUser(int sourceUserId) {
        this.sourceUserId = sourceUserId;
    }
}
