package venkov.vladimir.thebeginning.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.validatiors.base.Validator;


public class HttpUsersService implements UserService {

    private final Repository<User> mUserRepository;
    private final Validator<User> mUserValidator;

    public HttpUsersService(Repository<User> repository , Validator<User> validator) {
        mUserRepository = repository;
        mUserValidator = validator;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return mUserRepository.getAll();
    }

    @Override
    public User getDetailsById(int id) throws Exception {
        return mUserRepository.getById(id);
    }

    @Override
    public List<User> getFilteredUsers(String pattern) throws Exception {
        final String patternToLower = pattern.toLowerCase();

        return getAllUsers().stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(patternToLower) ||
                        user.getLastName().toLowerCase().contains(patternToLower))
                .collect(Collectors.toList());
    }



    @Override
    public User createUser(User user) throws IllegalArgumentException, IOException {
        if(!mUserValidator.isValid(user)){
            throw new IllegalArgumentException("User is not valid!");
        }else {

            return mUserRepository.add(user);
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws Exception {
        int b = 4;
        return getAllUsers().stream()
                .filter(x -> x.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllTenants() throws Exception {
        return getAllUsers().stream()
                .filter(x -> !x.getLandlord())
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllLandlords() throws Exception {
        return getAllUsers().stream()
                .filter(User::getLandlord)
                .collect(Collectors.toList());
    }

}
