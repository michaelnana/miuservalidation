package persistence;
import play.db.DB;

import java.sql.Connection;
import java.sql.Statement;

public class UsersDB {

    public boolean register(String user, String password) {
        Connection conn = DB.getConnection();
        try {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate("Insert into user (user, password) values ('" + user + "', '" + password + "')");
            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
