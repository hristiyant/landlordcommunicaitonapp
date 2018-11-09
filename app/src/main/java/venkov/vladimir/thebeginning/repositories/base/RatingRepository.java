package venkov.vladimir.thebeginning.repositories.base;

public interface RatingRepository {

    double getRatingById(int userId);

    double rateTakerUserByIdAndGiverUserId(int takerUserId, int giverUserId, double rating);

}
