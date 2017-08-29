package persistence;
import api.user.UserRegistration;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersDB {

    public UserRegistration register(String user, String password) {
        Connection conn = DB.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet res =  stmt.executeQuery("Select * from user where username = '" + user + "'");

            if (res.next()) {
                return new UserRegistration(false, "User already exists");
            } else {
                Statement insertStmt = conn.createStatement();
                int result = insertStmt.executeUpdate("Insert into user (username, password) values ('" + user + "', '" + password + "')");
                if (result == 0) {
                    return new UserRegistration(false, "Error on adding a new User");
                } else {
                    return new UserRegistration(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserRegistration(false, "Error on adding a new User");
    }
}
