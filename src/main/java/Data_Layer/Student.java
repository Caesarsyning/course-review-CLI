package Data_Layer;

public class Student {

private int id;
private String computing_ID;
private String password;

    public Student(int id, String computing_ID, String password) {
        this.id = id;
        this.computing_ID = computing_ID;
        this.password = password;
    }
    public Student(){}
    public Student( String computing_ID, String password) {
        this.id = id;
        this.computing_ID = computing_ID;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputing_ID() {
        return computing_ID;
    }

    public void setComputing_ID(String computing_ID) {
        this.computing_ID = computing_ID;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", computing_ID='" + computing_ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
