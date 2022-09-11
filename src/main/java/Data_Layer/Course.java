package Data_Layer;

public class Course {

private int id;
private String subject;
private int courseNum;
private String title;

    public Course(int id, String subject, int courseNum, String title) {
        this.id = id;
        this.subject = subject;
        this.courseNum = courseNum;
        this.title = title;
    }
    public Course( String subject, int courseNum, String title) {
        this.subject = subject;
        this.courseNum = courseNum;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", courseNum=" + courseNum +
                ", title='" + title + '\'' +
                '}';
    }
    public String toString2() {
        return "Course{" +
                "subject='" + subject + '\'' +
                ", courseNum=" + courseNum +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
