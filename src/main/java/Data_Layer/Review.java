package Data_Layer;

public class Review {
    private int id;
    private int studentID;
    private int courseID;
    private String text;
    private int rating;

    public Review( int studentID, int courseID, String text, int rating) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.text = text;
        this.rating = rating;
    }


    public Review(int id, int studentID, int courseID, String text, int rating) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;
        this.text = text;
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", studentID=" + studentID +
                ", courseID=" + courseID +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}