package venkov.vladimir.thebeginning.diconfig;


import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.Message;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.parsers.GsonJsonParser;
import venkov.vladimir.thebeginning.parsers.base.JsonParser;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<User> userJsonParser() {
        return new GsonJsonParser<>(User.class, User[].class);
    }
    @Provides
    public JsonParser<Message> messageJsonParser() {
        return new GsonJsonParser<>(Message.class, Message[].class);
    }
    @Provides
    public JsonParser<Accommodation> accommodationJsonParser() {
        return new GsonJsonParser<>(Accommodation.class, Accommodation[].class);
    }
    @Provides
    public JsonParser<Double> doubleJsonParser() {
        return new GsonJsonParser<>(Double.class, Double[].class);
    }
}