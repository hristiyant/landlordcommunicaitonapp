package venkov.vladimir.thebeginning.diconfig;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.http.OkHttpHttpRequester;
import venkov.vladimir.thebeginning.http.base.HttpRequester;
import venkov.vladimir.thebeginning.Constants;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
