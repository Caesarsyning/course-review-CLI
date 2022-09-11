package Data_Layer;

public class CourseWithReview {
    protected String subject;
    protected int Cata;
    protected String text;
    protected int rating;

    @Override
    public String toString() {
        return "CourseWithReview{" +
                "subject='" + subject + '\'' +
                ", Cata=" + Cata +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

    public CourseWithReview(String subject, int cata, String text, int rating) {
        this.subject = subject;
        Cata = cata;
        this.text = text;
        this.rating = rating;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCata() {
        return Cata;
    }

    public void setCata(int cata) {
        Cata = cata;
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