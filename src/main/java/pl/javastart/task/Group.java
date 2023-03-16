package pl.javastart.task;

public class Group {
    private String code;
    private String name;
    private int lecturerId;
    private static int codeCounter;
    private static String[] codeArray = new String[1];
    private static int studentCounter;
    private static Student[] groupStudents = new Student[1];

    public Group(String code, String name, int lecturerId) {
        codeCounter++;
        this.code = code;
        this.name = name;
        this.lecturerId = lecturerId;
        addCodeToArray(code);
        System.out.println("tworze nowa grupe o kodzie " + code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public static int getCodeCounter() {
        return codeCounter;
    }

    public static Student[] getGroupStudents() {
        return groupStudents;
    }


    public static String[] getCodeArray() {
        return codeArray;
    }

    public static int getStudentCounter() {
        return studentCounter;
    }


    private static void addCodeToArray(String code) {
        if (codeCounter == 1)
            codeArray[0] = code;
        if ((codeCounter > 1) && codeIsUnique(code)) {

            String[] codeArrayNew = new String[codeCounter];
            for(int i = 0; i < codeArray.length; i++) {
                codeArrayNew[i] = codeArray[i];
            }
            codeArrayNew[codeCounter - 1] = code;
            codeArray = codeArrayNew;
        }
    }

    public static boolean codeIsUnique(String code) {
        boolean codeUniqueCheck = true;
        for(int i = 0; i < codeArray.length; i++) {
            if (code.equals(codeArray[i]))
                codeUniqueCheck = false;
        }
        return codeUniqueCheck;
    }

    public static boolean codeExists(String groupCode) {
        boolean codeCheck = false;
        for (String code : codeArray) {
             if (groupCode.equals(code)) {
                 codeCheck = true;
             }
        }
        return codeCheck;
    }

    public static void addStudentToArray(int index, String groupCode, String firstName, String lastName) {
           
            Student[] temp = new Student[groupStudents.length + 1];
            for(int i = 0; i < groupStudents.length; i++) {
                temp[i] = groupStudents[i];
            }
            Student student = new Student(index, firstName, lastName);
            if (studentIsUniqueInGroup(student)) {
                temp[groupStudents.length] = student;
            }
            groupStudents = temp;
        }

    private static boolean studentIsUniqueInGroup(Student student) {
        boolean studentUniqueCheck = true;
        for(int i = 0; i < groupStudents.length; i++) {
            if (groupStudents[i].equals(student))
                studentUniqueCheck = false;
        }
        return studentUniqueCheck;
    }

    public static void printGroupStudents(Student[] gorupStudents) {
        for (int i = 0; i < groupStudents.length; i++) {
            System.out.println(groupStudents[i].getIndex()
                    + " " + groupStudents[i].getFirstName()
                    + " " + groupStudents[i].getLastName());
        }
    }
}
