package Database;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemeDatabase extends Database {

    protected Connection connection;
    MemeDatabase(String url,String user,String password){
        URL=url;
        USER=user;
        PASSWORD=password;
        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
