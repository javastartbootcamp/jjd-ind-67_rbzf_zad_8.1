package pl.javastart.task;

import java.util.Arrays;

public class UniversityApp {
    private Lecturer[] lecturers = new Lecturer[10];
    private int lecturersCount = 0;
    private Group[] groups = new Group[10];
    private int groupsCounter = 0;
    private Student[] students = new Student[10];
    private int studentsCount = 0;
    private GradeComplete[] gradesComplete = new GradeComplete[10];
    private int gradesCompleteCount = 0;

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (lecturerExists(id)) {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        } else {
            ensureLecturersCapacity();
            Lecturer lecturer = new Lecturer(firstName, lastName, id, degree);
            lecturers[lecturersCount] = lecturer;
            lecturersCount++;
        }
    }

    private void ensureLecturersCapacity() {
        if (lecturers.length <= lecturersCount) {
            lecturers = Arrays.copyOf(lecturers, lecturers.length * 2);
        }
    }

    private boolean lecturerExists(int id) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
    public void createGroup(String code, String name, int lecturerId) {
        if (groupExists(code)) {
            System.out.println("Grupa " + code + " już istnieje.");
        } else if (!lecturerExists(lecturerId)) {
            System.out.println("Prowadzacy o id " + lecturerId + " nie istnieje.");
        } else {
            Lecturer lecturer = findLectuer(lecturerId);
            ensureGroupsCapacity();
            Group group = new Group(code, name, lecturer);
            groups[groupsCounter] = group;
            groupsCounter++;
        }
    }

    private void ensureGroupsCapacity() {
        if (groups.length <= groupsCounter) {
            groups = Arrays.copyOf(groups, groups.length * 2);
        }
    }

    private Lecturer findLectuer(int lecturerId) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId() == lecturerId) {
                return lecturers[i];
            }
        }
        return null;
    }

    private boolean groupExists(String code) {
        for (int i = 0; i < groupsCounter; i++) {
            if (groups[i].getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = findGroup(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else {
            Student student = findStudent(index);
            if (student == null) {
                student = new Student(firstName, lastName, index);
                ensureStudentsCapacity();
                students[studentsCount] = student;
                studentsCount++;
                group.addStudent(student);
            } else if (studentBelongsToTheGroup(index, groupCode)) {
                System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
            } else {
                group.addStudent(student);
            }
        }
    }

    private void ensureStudentsCapacity() {
        if (students.length <= studentsCount) {
            students = Arrays.copyOf(students, students.length * 2);
        }
    }

    private Group findGroup(String groupCode) {
        for (int i = 0; i < groupsCounter; i++) {
            if (groups[i].getCode().equals(groupCode)) {
                return groups[i];
            }
        }
        return null;
    }

    private Student findStudent(int studentIndex) {
        for (int i = 0; i < studentsCount; i++) {
            if (students[i].getIndex() == studentIndex) {
                return students[i];
            }
        }
        return null;
    }

    /**
         * Wyświetla informacje o grupie w zadanym formacie.
         * Oczekiwany format:
         * Kod: [kod_grupy]
         * Nazwa: [nazwa przedmiotu]
         * Prowadzący: [stopień naukowy] [imię] [nazwisko]
         * Uczestnicy:
         * [nr indeksu] [imie] [nazwisko]
         * [nr indeksu] [imie] [nazwisko]
         * [nr indeksu] [imie] [nazwisko]
         * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
         *
         * @param groupCode - kod grupy, dla której wyświetlić informacje
         */
    public void printGroupInfo(String groupCode) {
        Group group = findGroup(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
            return;
        }
        group.printInfo();
    }

    /**
         * Dodaje ocenę końcową dla wskazanego studenta i grupy.
         * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
         * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
         * "Grupa pp-2022 nie istnieje"
         * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
         * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
         * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
         * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
         *
         * @param studentIndex - numer indeksu studenta
         * @param groupCode    - kod grupy
         * @param grade        - ocena
         */
    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group group = findGroup(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje.");
            return;
        }
        if (!studentBelongsToTheGroup(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
            return;
        }
        if (gradeExists(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
            return;
        }
        Student student = findStudent(studentIndex);
        ensureGradesCompleteCapacity();
        gradesComplete[gradesCompleteCount] = new GradeComplete(grade, student, group);
        gradesCompleteCount++;
    }

    private void ensureGradesCompleteCapacity() {
        if (gradesComplete.length <= gradesCompleteCount) {
            gradesComplete = Arrays.copyOf(gradesComplete, gradesComplete.length * 2);
        }
    }

    private boolean gradeExists(int studentIndex, String groupCode) {
        for (int i = 0; i < gradesCompleteCount; i++) {
            if (gradesComplete[i].getStudent().getIndex() == studentIndex
                    && gradesComplete[i].getGroup().getCode().equals(groupCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean studentBelongsToTheGroup(int studentIndex, String groupCode) {
        Group group = findGroup(groupCode);
        if (group != null && group.getStudents() != null) {
            return group.hasStudentWithIndex(studentIndex);
        }
        return false;
    }

    /**
         * Wyświetla wszystkie oceny studenta.
         * Przykładowy wydruk:
         * Podstawy programowania: 5.0
         * Programowanie obiektowe: 5.5
         *
         * @param index - numer indeksu studenta dla którego wyświetlić oceny
         */
    public void printGradesForStudent(int index) {
        for (int i = 0; i < gradesCompleteCount; i++) {
            if (gradesComplete[i].getStudent().getIndex() == index) {
                System.out.println(gradesComplete[i].getGroup().getName() + ": " + gradesComplete[i].getGrade());
            }
        }
    }

        /**
         * Wyświetla oceny studentów dla wskazanej grupy.
         * Przykładowy wydruk:
         * 179128 Marcin Abacki: 5.0
         * 179234 Dawid Donald: 4.5
         * 189521 Anna Kowalska: 5.5
         *
         * @param groupCode - kod grupy, dla której wyświetlić oceny
         */
    public void printGradesForGroup(String groupCode) {
        Group group = findGroup(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
            return;
        }
        for (int i = 0; i < gradesCompleteCount; i++) {
            if (gradesComplete[i].getGroup().getCode().equals(groupCode)) {
                for (int j = 0; j < group.getStudentsCounter(); j++) {
                    System.out.println(group.getStudents()[j].getInfo() + ": "
                            + getStudentGrade(group.getStudents()[j].getIndex()));
                }
                break;
            }
        }
    }

    private double getStudentGrade(int index) {
        double studentGrade = 0;
        for (int i = 0; i < gradesCompleteCount; i++) {
            if (gradesComplete[i].getStudent().getIndex() == index) {
                studentGrade = gradesComplete[i].getGrade();
            }
        }
        return studentGrade;
    }

    /**
         * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
         * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
         * Przykładowy wydruk:
         * 179128 Marcin Abacki
         * 179234 Dawid Donald
         * 189521 Anna Kowalska
         */
    public void printAllStudents() {
        for (int i = 0; i < studentsCount; i++) {
            System.out.println(students[i].getInfo());
        }
    }
}
