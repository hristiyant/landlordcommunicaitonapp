package venkov.vladimir.thebeginning.services;

import java.util.List;

import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;

public interface AccommodationService {
    List<Accommodation> getAllAccommodations() throws Exception;

    List<Accommodation> getAllAccommodationForLoggedUser(User loggedUser) throws Exception;

    Accommodation getDetailsById(int id) throws Exception;

    List<Accommodation> getFilteredAccommodations(String pattern, User user) throws Exception;

    Accommodation createAccommodation(Accommodation accommodation) throws Exception;
}
