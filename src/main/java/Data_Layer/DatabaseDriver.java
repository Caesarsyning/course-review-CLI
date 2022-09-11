package Data_Layer;
import java.sql.*;

public class DatabaseDriver {
    public static final String DATABASE_PATH = "CourseReview.db";

    Connection connection;

    public DatabaseDriver() throws SQLException {
        connect();
        disconnect();
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite::resource:"+DATABASE_PATH);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Application Error: Class is not Found");
        }
    }

    public void disconnect()  throws SQLException{
        connection.close();
    }
    public static void main(String[] args) throws SQLException {
        DatabaseDriver dd = new DatabaseDriver();
        dd.connect();
        System.out.println(dd.connection.toString());
        dd.disconnect();

    }


}