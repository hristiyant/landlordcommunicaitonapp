package venkov.vladimir.thebeginning.services;

import java.util.List;

import venkov.vladimir.thebeginning.models.User;

public interface UserService {
    List<User> getAllLandlordsOrTenantsAccordingToLoggedUser(User loggedUser) throws Exception;

    User getDetailsById(int id) throws Exception;

    List<User> getFilteredByNameLandLordsOrTenantsAccordingToLoggedUser(String pattern, User loggedUser) throws Exception;

    User createUser(User user) throws Exception;

    User getUserByPhoneNumber(String phoneNumber)throws Exception;
}