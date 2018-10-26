package venkov.vladimir.thebeginning.models;

public class Rating {

    private int id;

    private double rating;

    private User ratedUser;

    private User sourceUser;

    public Rating() {
    }

    public Rating(User ratedUser, User sourceUser, double rating) {
        this.rating = rating;
        this.ratedUser = ratedUser;
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

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }
}
