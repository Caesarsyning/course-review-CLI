package Data_Layer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDriver extends DatabaseDriver{

    public ReviewDriver() throws SQLException {
        connect();
        disconnect();
    }
    public List<Review> getReview() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        Statement terminal = connection.createStatement();
        String command = "select * from Reviews;";
        ResultSet rs = terminal.executeQuery(command);
        List<Review> reviews = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            int studentID = rs.getInt("Student_ID");
            int courseID = rs.getInt("Course_ID");
            String text = rs.getString("text");
            int rating = rs.getInt("rating");
            reviews.add(new Review(id,studentID,courseID,text,rating));
        }

        return reviews;

    }

    public void addReview(Review review) throws SQLException {
        int Student_id = review.getStudentID();
        int Course_id =review.getCourseID();
        String text = review.getText();
        int rating = review.getRating();
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        //Quote: https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/
        String command = "insert into Reviews(student_id,course_id, text, rating) values (?,?,?,?);";
        PreparedStatement terminal = connection.prepareStatement(command);
        terminal.setInt(1,Student_id);
        terminal.setInt(2,Course_id);
        terminal.setString(3,text);
        terminal.setInt(4,rating);
        terminal.execute();
    }

    public List<myReview> allMyReviews(Student student) throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        String command = "select Courses.Subject, Courses.Catalog_Number,Reviews.text,Reviews.rating " +
                "from Reviews inner join Courses on Reviews.Course_ID = Courses.id where Reviews.Student_ID=?;";

        PreparedStatement terminal = connection.prepareStatement(command);
        terminal.setInt(1, student.getId());
        ResultSet rs = terminal.executeQuery();
        List<myReview> myReviews = new ArrayList<>();
        while(rs.next()) {
            int rating = rs.getInt("rating");
            String subject = rs.getString("Subject");
            int catalogNumber = rs.getInt("Catalog_Number");
            String text = rs.getString("text");
            myReviews.add(new myReview(subject,catalogNumber,text,rating));
        }
        return myReviews;
    }
    public List<CourseWithReview> oneCourseAllReviews(String course) throws SQLException {
        String[] coursename = course.trim().split(" ");
        String subject = coursename[0];
        int Cata = Integer.parseInt(coursename[1]);
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        String command0 = "select Courses.id from Courses where Courses.Subject= ? and Courses.Catalog_Number =?;";

        PreparedStatement terminal0 = connection.prepareStatement(command0);
        terminal0.setString(1,subject);
        terminal0.setInt(2,Cata);
        ResultSet rs0 = terminal0.executeQuery();
        int Id = rs0.getInt("id");

        String command = "select Courses.Subject, Courses.Catalog_Number,Reviews.text,Reviews.rating " +
                "from Reviews, Courses where Reviews.Course_ID=? and Courses.id = ?;";
        PreparedStatement terminal = connection.prepareStatement(command);
        terminal.setInt(1,Id);
        terminal.setInt(2,Id);
        ResultSet rs = terminal.executeQuery();
        List<CourseWithReview> courseWReviews = new ArrayList<>();
        while(rs.next()) {
            int rating = rs.getInt("rating");
            String subj = rs.getString("Subject");
            int catalogNumber = rs.getInt("Catalog_Number");
            String text = rs.getString("text");
            courseWReviews.add(new CourseWithReview(subj,catalogNumber,text,rating));
        }

        return courseWReviews;
    }


}