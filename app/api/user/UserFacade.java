package api.user;

import persistence.UsersDB;

public class UserFacade implements UserAPI {
    @Override
    public String register(String user, String password) {
        if (new UsersDB().register(user, password)) {
            return "successful";
        } else {
            return "failure";
        }
    }
}
