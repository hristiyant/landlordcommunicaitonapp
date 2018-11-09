package venkov.vladimir.thebeginning.repositories;

import java.io.IOException;

import venkov.vladimir.thebeginning.Constants;
import venkov.vladimir.thebeginning.http.base.HttpRequester;
import venkov.vladimir.thebeginning.models.Rating;
import venkov.vladimir.thebeginning.parsers.base.JsonParser;
import venkov.vladimir.thebeginning.repositories.base.RatingRepository;

public class RatingRepositoryImpl implements RatingRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser mJsonParser;

    public RatingRepositoryImpl(HttpRequester mHttpRequester, String mServerUrl, JsonParser mJsonParser) {
        this.mHttpRequester = mHttpRequester;
        this.mServerUrl = mServerUrl;
        this.mJsonParser = mJsonParser;
    }

    @Override
    public double getRatingById(int userId) {
        String url = mServerUrl + "/" + userId + "/rating";
        String json = null;
        try {
            json = mHttpRequester.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (double) mJsonParser.fromJson(json);
    }

    @Override
    public double rateTakerUserByIdAndGiverUserId(int takerUserId, int giverUserId, double rating) {
        String url =Constants.BASE_SERVER_URL + "/Ratings" + "/" + takerUserId + "/" + giverUserId + "/" + rating;
        String json = null;
        try {
            json = mHttpRequester.put(url, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Rating ratingReturned = (Rating) mJsonParser.fromJson(json);
//        double ratingReturnedToDouble = ratingReturned.getRating();

        return rating;
    }
}
