package Data_Layer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDriver extends DatabaseDriver{


    public StudentDriver() throws SQLException {
        connect();
        disconnect();
    }

    public List<Student> getStudents() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        Statement terminal = connection.createStatement();
        String command = "SELECT * FROM Students;";

        ResultSet rs = terminal.executeQuery(command);
        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");

            String cmpID = rs.getString("computing_ID");
            String password = rs.getString("password");
            students.add(new Student(id, cmpID, password));

        }
        return students;
    }

    public void addStudentToDB(String computing_id, String passw) throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        //Quote: https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/
        String command = "insert into Students(computing_ID,password) values (?,?);";
        PreparedStatement terminal = connection.prepareStatement(command);
        terminal.setString(1,computing_id);
        terminal.setString(2,passw);
        terminal.execute();
    }

}