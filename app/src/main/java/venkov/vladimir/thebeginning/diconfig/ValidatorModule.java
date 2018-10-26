package venkov.vladimir.thebeginning.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.validatiors.UserValidator;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

@Module
public class ValidatorModule {
    @Provides
    @Singleton
    public Validator<User> quoteValidator(){
        return new UserValidator();
    }
}

