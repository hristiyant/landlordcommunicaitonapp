package venkov.vladimir.thebeginning.repositories;

import java.io.IOException;
import java.util.List;

import venkov.vladimir.thebeginning.http.base.HttpRequester;
import venkov.vladimir.thebeginning.parsers.base.JsonParser;
import venkov.vladimir.thebeginning.repositories.base.Repository;

public class HttpRepository<T> implements Repository<T> {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<T> mJsonParser;

    public HttpRepository(HttpRequester httpRequester, String serverUrl, JsonParser<T> jsonParser) {
        mHttpRequester = httpRequester;
        mServerUrl = serverUrl;
        mJsonParser = jsonParser;
    }

    @Override
    public List<T> getAll() throws IOException {
        String jsonArray = null;
        jsonArray = mHttpRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(jsonArray);
    }

    @Override
    public T add(T item) throws IOException {
        String url = mServerUrl + "/new";
        String requestBody = mJsonParser.toJson(item);
        String responseBody = mHttpRequester.post(url, requestBody);

        mJsonParser.fromJson(responseBody) ;
        return item;
    }

    @Override
    public T edit(T item, int id) throws IOException {
        //todo later impl
        return null;
    }

    @Override
    public T getById(int id) throws IOException {
        String url = mServerUrl + "/" + id;
        String json = mHttpRequester.get(url);
        return mJsonParser.fromJson(json);
    }
}