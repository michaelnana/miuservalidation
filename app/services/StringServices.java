package services;

public final class StringServices {

    public static boolean checkValidity(String wordToCheck, String regex) {
        if (wordToCheck != null) {
            return wordToCheck.matches(regex);
        } else {
            return false;
        }
    }
}
