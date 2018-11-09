package venkov.vladimir.thebeginning.repositories.base;

import java.io.IOException;

public interface RatingRepository {

    double getRatingById(int userId);

    void createOrUpdateUserRating(int ratedUserId, int giverUserId, double ratingValue) throws IOException;

}
