package venkov.vladimir.thebeginning.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.base.RatingRepository;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.validatiors.base.Validator;


public class HttpUsersService implements UserService {

    private final Repository<User> mUserRepository;
    private final Validator<User> mUserValidator;
    private final RatingRepository mRatingRepository;

    public HttpUsersService(Repository<User> repository , Validator<User> validator,
                            RatingRepository ratingRepository)
    {
        mUserRepository = repository;
        mUserValidator = validator;
        mRatingRepository = ratingRepository;
    }

    @Override
    public List<User> getAllLandlordsOrTenantsAccordingToLoggedUser(User loggedUser) throws Exception {
        if (loggedUser.getLandlord()) {
            return getAllTenants();
        } else {
            return getAllLandlords();
        }
    }

    private List<User> getAllUsersPlusRating() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        mUserRepository.getAll().forEach(x ->{ x.setRating(mRatingRepository.getRatingById(x.getId()));
            users.add(x); });

        User uss = users.get(0);
        int a = 0;

        return users;
    }

    private List<User> getAllTenants() throws Exception {
        return getAllUsersPlusRating().stream()
                .filter(x -> !x.getLandlord())
                .collect(Collectors.toList());
    }

    private List<User> getAllLandlords() throws Exception {
        return getAllUsersPlusRating().stream()
                .filter(User::getLandlord)
                .collect(Collectors.toList());
    }

    @Override
    public User getDetailsById(int id) throws Exception {
        User user = mUserRepository.getById(id);
        user.setRating(mRatingRepository.getRatingById(id));
        return user;

    }

    @Override
    public List<User> getFilteredByNameLandLordsOrTenantsAccordingToLoggedUser(String pattern, User loggedUser) throws Exception {
        final String patternToLower = pattern.toLowerCase();

        return getAllLandlordsOrTenantsAccordingToLoggedUser(loggedUser).stream()
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
        return mUserRepository.getAll().stream()
                .filter(x -> x.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }
}
