package parkingLot.validator;

/**
 * Validates whether a string is an integer or not.
 */
public class IntegerValidator {

    /**
     * Checks if given string is an integer.
     *
     * @param input Input string.
     * @return Boolean indicating whether input string is an integer or not.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
