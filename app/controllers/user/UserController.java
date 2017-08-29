package controllers.user;

import api.user.UserAPI;
import api.user.UserFacade;
import api.user.UserRegistration;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
    UserAPI userAPI = new UserFacade();

    public Result register() {
        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        boolean validUsername = checkValidity(username, "^[A-Za-z0-9]{5,}$");
        String password = form.get("password");
        boolean validPassword = checkValidity(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$");

        if (validUsername && validPassword) {
            return apiRegisterResult(userAPI.register(username, password));
        } else {
            ObjectNode result = Json.newObject();
            result.put("registrationSuccessful", false);
            result.put("errorMessage", "Username or password wasn't valid");
            return notFound(result);
        }
    }

    private Result apiRegisterResult (UserRegistration userResult) {
        ObjectNode json = Json.newObject();
        if (userResult.getSuccess()) {
            json.put("registrationSuccessful", userResult.getSuccess());
            return ok(json);
        } else {
            json.put("registrationSuccessful", userResult.getSuccess());
            json.put("errorMessage", userResult.getErrorMessage());
            return notFound(json);
        }
    }

    private boolean checkValidity(String wordToCheck, String regex) {
        if (wordToCheck != null) {
            return wordToCheck.matches(regex);
        } else {
            return false;
        }
    }
}
