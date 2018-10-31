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
        return mAccommodationRepository.getAll();
    }

    @Override
    public Accommodation getDetailsById(int id) throws Exception {
        return mAccommodationRepository.getById(id);
    }

    @Override
    public List<Accommodation> getFilteredAccommodations(String pattern) throws Exception {
        final String patternToLower = pattern.toLowerCase();

        return getAllAccommodations().stream()
                .filter(accommodation -> accommodation.getAddress().toLowerCase().contains(patternToLower))
                .collect(Collectors.toList());
    }



    @Override
    public Accommodation createAccommodation(Accommodation accommodation) throws IllegalArgumentException, IOException {
        if(!mAccommodationValidator.isValid(accommodation)){
            throw new IllegalArgumentException("User is not valid!");
        }else {

            return mAccommodationRepository.add(accommodation);
        }
    }

    @Override
    public Accommodation getAccommodationByPhoneNumber(String phoneNumber) throws Exception {
        //int b = 4;
        return getAllAccommodations().stream()
                .filter(x -> x.getLandlord().getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getTenant() throws Exception {
        return this.getTenant();
    }

    @Override
    public User getLandlord() throws Exception {
        return this.getLandlord();
    }
}
