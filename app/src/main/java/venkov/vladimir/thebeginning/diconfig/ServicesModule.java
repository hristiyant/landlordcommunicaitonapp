package venkov.vladimir.thebeginning.diconfig;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.services.HttpUsersService;
import venkov.vladimir.thebeginning.services.UserService;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

@Module
public class ServicesModule {
    @Provides
    public UserService userService(Repository<User> repository, Validator<User> validator) {
        return new HttpUsersService(repository, validator);
    }
}
