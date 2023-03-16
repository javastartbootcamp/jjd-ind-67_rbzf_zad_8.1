package pl.javastart.task;

import java.util.Arrays;

public class Main {

    // uzupełnij metody w UniversityApp zgodnie z dokumentacją
    public static void main(String[] args) {
        UniversityApp universityApp = new UniversityApp();

        universityApp.createLecturer(1, "dr", "Janusz", "Rataj");
        universityApp.createLecturer(2, "dr", "Janusz", "Lataj");
        System.out.println(Arrays.toString(Lecturer.getLecturerIdArray()));

        universityApp.createGroup("pp-2022", "Podstawy Programowania", 1);
        universityApp.createGroup("po-2022", "Podstawy Programowania", 3);
        universityApp.addStudentToGroup(179128, "pp-2022", "Marcin", "Abacki");
      //  universityApp.addStudentToGroup(179129, "pp-2022", "Adam", "Browarski");
      //universityApp.printAllStudents();

     //   universityApp.addGrade(179128, "pp-2022", 5);
      //  universityApp.addGrade(179129, "pp-2022", 5);
      //  universityApp.addGrade(179128, "po-2022", 5.5);

      //  universityApp.printGroupInfo("pp-2022");
      //  universityApp.printGradesForStudent(179128);
      //  universityApp.printGradesForGroup("pp-2022");
    }
}
