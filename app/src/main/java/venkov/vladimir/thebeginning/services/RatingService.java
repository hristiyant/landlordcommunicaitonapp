package venkov.vladimir.thebeginning.services;

public interface RatingService {

    double getUserRatingByID(int userId) throws Exception;

    double rateTakerUserByIdAndGiverUserId(int takerUserId, int giverUserId, double rating);
}
