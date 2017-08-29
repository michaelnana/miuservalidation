package api.user;

public class UserRegistration {
    boolean successFulRegistration = false;
    String errorMessage = "";

    public UserRegistration(boolean success, String error) {
        successFulRegistration = success;
        errorMessage = error;
    }

    public UserRegistration(boolean success) {
        successFulRegistration = success;
    }

    public boolean getSuccess () {
        return successFulRegistration;
    }

    public String getErrorMessage () {
        return errorMessage;
    }
}
