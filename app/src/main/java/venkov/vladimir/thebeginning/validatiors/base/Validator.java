package venkov.vladimir.thebeginning.validatiors.base;


public interface Validator<T> {
    boolean isValid(T object);
}