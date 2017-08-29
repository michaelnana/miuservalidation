package api.user;

import persistence.UsersDB;

public class UserFacade implements UserAPI {

    @Override
    public UserRegistration register(String user, String password) {
        return new UsersDB().register(user, password);
    }
}
