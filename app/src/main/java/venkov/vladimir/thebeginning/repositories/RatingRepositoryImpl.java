package venkov.vladimir.thebeginning.repositories;

import java.io.IOException;

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
        String url = mServerUrl + "/Users/" + userId + "/rating";
        String json = null;
        try {
            json = mHttpRequester.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double a = (double) mJsonParser.fromJson(json);
        int b = 5;

        return a;
    }

    @Override
    public void createOrUpdateUserRating(int ratedUserId, int giverUserId, double ratingValue) throws IOException {
        String url = mServerUrl + "/Ratings/" + ratedUserId + "/" + giverUserId + "/" + ratingValue;
        String json = null;
        String requestBody = mJsonParser.toJson(new Rating(ratedUserId, giverUserId, ratingValue));
        String responseBdy = mHttpRequester.put(url, requestBody);

        /*String json = null;
        json = mHttpRequester.get(url);

        double a = (double) mJsonParser.fromJson(json);

        int bp = 1;

        return a;*/
    }
}
