package venkov.vladimir.thebeginning.services;

import java.io.IOException;

public interface RatingService {

    double getUserRatingByID(int userId) throws Exception;

    void createOrUpdateUserRating(int ratedUserId, int giverUserId, double ratingValue) throws IOException, Exception;
}
