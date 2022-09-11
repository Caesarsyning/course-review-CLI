package Controller;

import Data_Layer.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    protected JSONObject root;
    protected JSONArray Courses;
    private StudentDriver studentDriver;
    private CourseDriver courseDriver;
    private ReviewDriver reviewDriver;
    protected List<Student> students;
    protected List<Course> courses;
    protected List<Review> reviews;
    protected List<myReview> myReviews;
    protected Student theUser;


    public MainController() throws SQLException, IOException {

        this.studentDriver = new StudentDriver();
        this.courseDriver = new CourseDriver();
        this.reviewDriver = new ReviewDriver();
        getTables();

    }

    public void getTables() throws SQLException {
        studentDriver.connect();
        students = studentDriver.getStudents();
        studentDriver.disconnect();
        courseDriver.connect();
        courses = courseDriver.getCourses();
        courseDriver.disconnect();
        reviewDriver.connect();
        reviews = reviewDriver.getReview();
        reviewDriver.disconnect();
    }

    public void getStudentTable() throws SQLException {
        studentDriver.connect();
        students = studentDriver.getStudents();
        studentDriver.disconnect();
    }

    public boolean computingIDNotExist(String computing_ID) {
        for (Student s : students) {
            if (computing_ID.equals(s.getComputing_ID()))
                return false;
        }
        return true;
    }

    public boolean courseExist(String course) {
        for (Course c : courses) {
            String subject = c.getSubject().trim() + " " + c.getCourseNum();
            subject = subject.trim();
            if (subject.equals(course))
                return true;
        }
        return false;
    }

    public boolean hasTwoReview(Student student, String course) throws SQLException {
        reviewDriver.connect();
        List<myReview> mReviews = reviewDriver.allMyReviews(student);
        reviewDriver.disconnect();
        for (myReview mr : mReviews) {
            String courseName = mr.getSubject() + " " + mr.getCatalogNumber();
            courseName = courseName.trim();
            if (courseName.equals(course))
                return true;
        }
        return false;

    }

    public void addNewUser(String name, String password) throws SQLException {
        studentDriver.connect();
        studentDriver.addStudentToDB(name, password);
        studentDriver.disconnect();
    }


    public boolean passwordExist(String name, String password) {
        for (Student s : students) {
            if (name.equals(s.getComputing_ID()) && password.equals(s.getPassword()))
                return true;
        }
        return false;
    }

    public Student getStudent(String name) throws SQLException {
        studentDriver.connect();
        students = studentDriver.getStudents();
        studentDriver.disconnect();
        for (Student s : students) {
            if (name.equals(s.getComputing_ID()))
                return s;
        }
        return null;
    }

    public void addTextReview(Student s, String course, String text, int rating) throws SQLException {
        for (Course c : courses) {
            String coursename = c.getSubject() + " " + c.getCourseNum();
            coursename = coursename.trim();
            if (coursename.equals(course)) {
                reviewDriver.connect();
                reviewDriver.addReview(new Review(s.getId(), c.getId(), text, rating));
                reviewDriver.disconnect();
            }

        }
    }

    public List<myReview> getAllMyReviews(Student s) throws SQLException {
        reviewDriver.connect();
        List<myReview> myReviews1 = reviewDriver.allMyReviews(s);
        reviewDriver.disconnect();
        return myReviews1;
    }

    public List<String> getOneCourseAllReviews(String course) throws SQLException {
        reviewDriver.connect();
        List<CourseWithReview> CwithReview = reviewDriver.oneCourseAllReviews(course);
        reviewDriver.disconnect();
        List<String> textCollection = new ArrayList<>();
        for (CourseWithReview r : CwithReview) {
            textCollection.add(r.getText());
        }
        return textCollection;
    }

    public double getAverageRating(String course) throws SQLException {
        reviewDriver.connect();
        List<CourseWithReview> CwithReview = reviewDriver.oneCourseAllReviews(course);
        reviewDriver.disconnect();
        double l = 0;
        double n = 0;
        for (CourseWithReview r : CwithReview) {
            l = l + r.getRating();
            n++;
        }
        double output =0;
            output = l / n;
        return output;
    }

    //    public void addToJsonReview() throws SQLException {
//        courseDriver.disconnect();
//        List<CourseWithReview> allreviews = .oneCourseAllReviews(name);
//        for (CourseWithReview cr : allreviews) {
//            reviewDriver.connect();
//            JSONObject oneReview = new JSONObject();
//            oneReview.put("review_id", cr.getId());
//            oneReview.put("message", cr.getText());
//            oneReview.put("score", cr.getRating());
//            OneCourse.put("reviews", oneReview);
//            reviewDriver.disconnect();
//        }
//    }
    public void addToJson(String filename) throws SQLException, IOException {
        getTables();
        root = new JSONObject();
        Courses = new JSONArray();
        for (Course cs : courses) {
            int courseID = cs.getId();
            boolean found = false;
            List<Review> cReview = new ArrayList<>();
            for (Review r : reviews) {
                if (courseID == r.getCourseID()) {
                    found = true;
                    cReview.add(r);
                }
            }
            if (!found) {
                continue;
            }
            JSONObject OneCourse = new JSONObject();
            OneCourse.put("course_id", cs.getId());
            OneCourse.put("subject", cs.getSubject());
            OneCourse.put("catalog_number", cs.getCourseNum());
            OneCourse.put("title", cs.getTitle());
            String name = cs.getSubject() + " " + cs.getCourseNum();
            name = name.trim();
            OneCourse.put("Average_Rating", getAverageRating(name));
            JSONArray revs = new JSONArray();
            for (Review rev : cReview) {
                JSONObject oneReview = new JSONObject();
                oneReview.put("review_id", rev.getId());
                oneReview.put("message", rev.getText());
                oneReview.put("score", rev.getRating());
                revs.put(oneReview);
            }
            OneCourse.put("reviews", revs);
            Courses.put(OneCourse);
        }
        root.put("courses", Courses);
        FileWriter file = new FileWriter(filename);
        file.write(root.toString(2));
        file.close();


    }

}


