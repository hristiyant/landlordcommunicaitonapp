package venkov.vladimir.thebeginning.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import venkov.vladimir.thebeginning.models.Accommodation;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.repositories.base.Repository;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

public class HttpAccommodationsService implements AccommodationService{

    private final Repository<Accommodation> mAccommodationRepository;
    private final Validator<Accommodation> mAccommodationValidator;

    public HttpAccommodationsService(Repository<Accommodation> repository , Validator<Accommodation> validator) {
        mAccommodationRepository = repository;
        mAccommodationValidator = validator;
    }

    @Override
    public List<Accommodation> getAllAccommodations() throws Exception {
        List<Accommodation> all = mAccommodationRepository.getAll();
        return mAccommodationRepository.getAll();
    }

    @Override
    public List<Accommodation> getAllAccommodationForLoggedUser(User loggedUser) throws Exception {
        return getAllAccommodations().stream()
                .filter(x -> x.getTenant().getId() == loggedUser.getId() ||
                x.getLandlord().getId() == loggedUser.getId())
                .collect(Collectors.toList());
    }

    @Override
    public Accommodation payRentForAccommodation(int accommodationId, Accommodation accommodation) throws Exception {
       return mAccommodationRepository.edit(accommodation, accommodationId, "pay/");
    }

    @Override
    public Accommodation getDetailsById(int id) throws Exception {
        return mAccommodationRepository.getById(id);
    }

    @Override
    public List<Accommodation> getFilteredAccommodations(String pattern, User loggedUser) throws Exception {
        final String patternToLower = pattern.toLowerCase();

        return getAllAccommodationForLoggedUser(loggedUser).stream()
                .filter(accommodation -> accommodation.getAddress().toLowerCase().contains(patternToLower))
                .collect(Collectors.toList());
    }



    @Override
    public Accommodation createAccommodation(Accommodation accommodation) throws Exception {
        if(!mAccommodationValidator.isValid(accommodation)){
            throw new IllegalArgumentException("Accommodation is not valid!");
        }else {
            return mAccommodationRepository.add(accommodation);
        }
    }

}
