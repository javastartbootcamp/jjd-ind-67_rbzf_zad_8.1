package pl.javastart.task;

public class Group {
    private static final int MAX_NO_STUDENTS_IN_GROUP = 30;
    private String code;
    private String name;
    private Lecturer lecturer;
    private Student[] students = new Student[MAX_NO_STUDENTS_IN_GROUP];
    private int studentsCounter = 0;

    public Group(String code, String name, Lecturer lecturer) {
        this.code = code;
        this.name = name;
        this.lecturer = lecturer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public int getStudentsCounter() {
        return studentsCounter;
    }

    public void setStudentsCounter(int studentsCounter) {
        this.studentsCounter = studentsCounter;
    }
}