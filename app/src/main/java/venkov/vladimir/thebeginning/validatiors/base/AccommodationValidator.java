package venkov.vladimir.thebeginning.validatiors.base;

import venkov.vladimir.thebeginning.Constants;
import venkov.vladimir.thebeginning.models.Accommodation;

public class AccommodationValidator implements Validator<Accommodation>{
    @Override
    public boolean isValid(Accommodation accommodation) {
        return isAddressValid(accommodation) &&
                accommodation != null;
    }

    public String showMessage() {
        String message = String.format("Address should be %s - %s long\n",
                Constants.ACCOMMODATION_ADDRESS_MIN_LENGTH,
                Constants.ACCOMMODATION_ADDRESS_MAX_LENGTH);

        return  message;
    }

    private boolean isAddressValid(Accommodation accommodation) {
        return (accommodation.getAddress().length() >= Constants.ACCOMMODATION_ADDRESS_MIN_LENGTH &&
                accommodation.getAddress().length() <= Constants.ACCOMMODATION_ADDRESS_MAX_LENGTH);
    }
}
