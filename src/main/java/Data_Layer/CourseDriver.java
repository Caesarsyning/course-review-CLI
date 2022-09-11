package Data_Layer;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CourseDriver extends DatabaseDriver {
    protected List<Course> uvaCourse;

    public CourseDriver() throws SQLException {
        connect();
        disconnect();
    }

    public List<Course> getCourses() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        Statement terminal = connection.createStatement();
        String command = "select * from Courses;";
        ResultSet rs = terminal.executeQuery(command);
        List<Course> courses = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String subject = rs.getString("Subject");
            int catalogNumber = rs.getInt("Catalog_Number");
            String title = rs.getString("Class_Title");
            courses.add(new Course(id, subject, catalogNumber, title));
        }

        return courses;

    }

    public boolean isExist(Course course) throws SQLException {
        List<Course> exist = new ArrayList<>();
        exist = getCourses();
        for (Course c : exist) {
            if (course.getSubject().equals(c.getSubject()) && course.getCourseNum() == c.getCourseNum()) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(Course course) throws SQLException {
        int id = course.getId();
        String subject = course.getSubject();
        int courseNum = course.getCourseNum();
        String title = course.getTitle();
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        //Quote: https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/
        String command = "insert into Courses(Subject,Catalog_Number,Class_Title) values (?,?,?);";
        PreparedStatement terminal = connection.prepareStatement(command);
        terminal.setString(1, subject);
        terminal.setInt(2, courseNum);
        terminal.setString(3, title);
        terminal.execute();
    }

    public void addUVAdataToDB() throws SQLException {
        for (Course c : uvaCourse) {
            if (!isExist(c))
                addCourse(c);
        }

    }
}