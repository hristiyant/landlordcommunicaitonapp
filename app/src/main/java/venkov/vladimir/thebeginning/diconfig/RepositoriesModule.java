package venkov.vladimir.thebeginning.diconfig;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.http.base.HttpRequester;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.parsers.base.JsonParser;
import venkov.vladimir.thebeginning.repositories.HttpRepository;
import venkov.vladimir.thebeginning.repositories.base.Repository;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public Repository<User> userRepository(
            @Named("baseServerUrl") String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<User> jsonParser
    ) {
        String url = baseServerUrl + "/Users";
        return new HttpRepository<>(httpRequester, url, jsonParser);
    }
}
