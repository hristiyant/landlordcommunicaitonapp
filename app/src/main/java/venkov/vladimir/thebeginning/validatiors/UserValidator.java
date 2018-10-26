package venkov.vladimir.thebeginning.validatiors;


import venkov.vladimir.thebeginning.Constants;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.validatiors.base.Validator;

public class UserValidator implements Validator<User> {
    @Override
    public boolean isValid(User user) {
        return isFirstNameValid(user) &&
                isLastNameValid(user) &&
                isPhoneNumberValid(user) &&
                user != null;
    }

    public String showMessage() {
        String message = String.format("Quote User's last name should be %s - %s long\n" +
                        "first name %s - %s long and \n" +
                        "Phone number length: %s  characters long \n" +
                        "(phone number should be in format: 0888123456)!",
                Constants.USER_LAST_NAME_MIN_LENGTH,
                Constants.USER_LAST_NAME_MAX_LENGTH,
                Constants.USER_FIRST_NAME_MIN_LENGTH,
                Constants.USER_FIRST_NAME_MAX_LENGTH,
                Constants.PHONE_NUMBER_LENGTH);
        return  message;
    }

    private boolean isFirstNameValid(User user) {
        return user.getFirstName().length() >= Constants.USER_FIRST_NAME_MIN_LENGTH &&
                user.getFirstName().length() <= Constants.USER_FIRST_NAME_MAX_LENGTH;
    }

    private boolean isLastNameValid(User  user) {
        return user.getLastName().length() >= Constants.USER_LAST_NAME_MIN_LENGTH &&
                user.getLastName().length() <= Constants.USER_LAST_NAME_MAX_LENGTH;
    }

    private boolean isPhoneNumberValid(User user) {
        return user.getPhoneNumber().length() == Constants.PHONE_NUMBER_LENGTH
//                &&user.getPhoneNumber().contains("1-9*")
                                                            ;
    }

}