package venkov.vladimir.thebeginning.services;

import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.base.RatingRepository;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

public class HttpRatingService implements RatingService {

    private final RatingRepository mRatingRepository;
    //private final Validator<User> mUserValidator;


    public HttpRatingService(RatingRepository ratingRepository) {
        this.mRatingRepository = ratingRepository;
    }


    @Override
    public double getUserRatingByID(int userId) throws Exception {
        return mRatingRepository.getRatingById(userId);
    }

    @Override
    public double rateTakerUserByIdAndGiverUserId(int takerUserId, int giverUserId, double rating) {
        return mRatingRepository.rateTakerUserByIdAndGiverUserId(takerUserId, giverUserId, rating);
    }
}
