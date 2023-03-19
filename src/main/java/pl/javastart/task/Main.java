package pl.javastart.task;

import java.util.Arrays;

public class Main {

    // uzupełnij metody w UniversityApp zgodnie z dokumentacją
    public static void main(String[] args) {
        UniversityApp universityApp = new UniversityApp();

        universityApp.createLecturer(1, "dr", "Janusz", "Rataj");
        universityApp.createLecturer(2, "dr", "Janusz", "Lataj");


        universityApp.createGroup("pp-2022", "Podstawy Programowania", 1);
        universityApp.createGroup("po-2022", "Programowanie Obiektowe", 2);
        universityApp.addStudentToGroup(179128, "pp-2022", "Marcin", "Abacki");
        universityApp.addStudentToGroup(179128, "po-2022", "Marcin", "Abacki");
        universityApp.addStudentToGroup(179129, "pp-2022", "Adam", "Browarski");
        universityApp.addStudentToGroup(189521, "po-2022", "Anna", "Kowalska");
        universityApp.printAllStudents();

        universityApp.addGrade(179128, "pp-2022", 5);
        universityApp.addGrade(179129, "pp-2022", 5.4);
        universityApp.addGrade(179128, "po-2022", 5.5);

        universityApp.printGroupInfo("pp-2022");
        universityApp.printGroupInfo("po-2022");
        universityApp.printGradesForStudent(179129);
        universityApp.printGradesForGroup("po-2022");
    }
}
