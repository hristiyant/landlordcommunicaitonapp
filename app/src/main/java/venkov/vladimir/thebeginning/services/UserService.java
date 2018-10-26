package venkov.vladimir.thebeginning.services;

import java.util.List;

import venkov.vladimir.thebeginning.models.User;

public interface UserService {
    List<User> getAllUsers() throws Exception;

    User getDetailsById(int id) throws Exception;

    List<User> getFilteredUsers(String pattern) throws Exception;

    User createUser(User user) throws Exception;
}