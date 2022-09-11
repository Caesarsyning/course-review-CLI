package Data_Layer;

public class myReview {
    protected int rating ;
    protected String subject ;
    protected int catalogNumber;
    protected String text ;

    public myReview( String subject, int catalogNumber, String text, int rating) {
        this.rating = rating;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "myReview{" +
                "rating=" + rating +
                ", subject='" + subject + '\'' +
                ", catalogNumber=" + catalogNumber +
                ", text='" + text + '\'' +
                '}';
    }
}
