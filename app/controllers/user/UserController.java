package controllers.user;

import api.user.UserFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public Result register() {
        boolean validUsername = false;
        boolean validPassword = false;
        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        if (username != null) {
            validUsername = username.matches("^[A-Za-z0-9]{5,}$");
        }
        String password = form.get("password");
        if (password != null) {
            validPassword = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$");
        }
        if (validUsername && validPassword) {
            String resultValue = new UserFacade().register(username, password);
            ObjectNode result = Json.newObject();
            result.put("registrationSuccessful", true);
            result.put("user", resultValue);
            return ok(result);
        } else {
            ObjectNode result = Json.newObject();
            result.put("registrationSuccessful", false);
            result.put("errorMessage", "Username or password wasn't valid");
            return notFound(result);
        }
    }
}
