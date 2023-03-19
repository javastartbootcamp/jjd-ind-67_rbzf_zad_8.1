package pl.javastart.task;


public class UniversityApp {
    private static final int MAX_NO_LECTURER = 100;
    private Lecturer[] lecturers = new Lecturer[MAX_NO_LECTURER];
    private int lecturersCount = 0;
    private static final int MAX_NO_GROUP = 100;
    private Group[] groups = new Group[MAX_NO_GROUP];
    private int groupsCounter = 0;
    private static final int MAX_TOTAL_NO_STUDENTS = 100;
    private Student[] students = new Student[MAX_TOTAL_NO_STUDENTS];
    private int studentsCount = 0;
    private static final int MAX_NO_GRADES = 200;
    private Grade[] grades = new Grade[MAX_NO_GRADES];
    private int gradesCount = 0;
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
            Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
            lecturers[lecturersCount] = lecturer;
            lecturersCount++;
        }
    }

    private boolean lecturerExists(int id) {
        boolean lecturerCheck = false;
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId() == id) {
                lecturerCheck = true;
            }
        }
            return lecturerCheck;
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
        public void createGroup (String code, String name, int lecturerId){
            if (groupExists(code)) {
                System.out.println("Grupa " + code + " juz istnieje.");
            } else if (!lecturerExists(lecturerId)) {
                System.out.println("Prowadzacy o id " + lecturerId + " nie istnieje.");
            } else {
                Lecturer lecturer = findLectuer(lecturerId);
                Group group = new Group(code, name, lecturer);
                groups[groupsCounter] = group;
                groupsCounter++;
            }
        }

    private Lecturer findLectuer(int lecturerId) {
            int indexLecturer = 0;
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId() == lecturerId) {
                indexLecturer = i;
            }
        }
        return lecturers[indexLecturer];
    }

    private boolean groupExists(String code) {
        boolean groupCheck = false;
        for (int i = 0; i < groupsCounter; i++) {
            if (groups[i].getCode().equals(code)) {
                groupCheck = true;
            }
        }
            return groupCheck;
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
        public void addStudentToGroup ( int index, String groupCode, String firstName, String lastName) {
            if (!groupExists(groupCode)) {
                System.out.println("Grupa " + groupCode + " nie istnieje.");
            } else {
                int indexGroup = findGroup(groupCode);
                int counter = groups[indexGroup].getStudentsCounter();
                Student student = new Student(index, firstName, lastName);
                if (!studentExists(index)) {

                    students[studentsCount] = student;
                    studentsCount++;
                    groups[indexGroup].getStudents()[counter] = student;
                    counter++;
                    groups[indexGroup].setStudentsCounter(counter);
                } else {

                    groups[indexGroup].getStudents()[counter] = student;
                    counter++;
                    groups[indexGroup].setStudentsCounter(counter);
                }
            }
        }

    private boolean studentExists(int index) {
            boolean studentCheck = false;
        for (int i = 0; i < studentsCount; i++) {
            if (students[i].getIndex() == index) {
                studentCheck = true;
            }
        }
        return studentCheck;
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
        public void printGroupInfo (String groupCode){
            System.out.println("Kod: " + groupCode);
            int indexGroup = findGroup(groupCode);
            System.out.println("Nazwa " + groups[indexGroup].getName());
            System.out.println("Prowadzacy: " + groups[indexGroup].getLecturer().getDegree()
                                + " " + groups[indexGroup].getLecturer().getFirstName()
                                + " " +groups[indexGroup].getLecturer().getLastName());
            System.out.println("Uczestnicy:");
            for (int i = 0; i < groups[indexGroup].getStudentsCounter(); i++) {
                System.out.println(groups[indexGroup].getStudents()[i].getStudentInfo());
            }
       }

    private int findGroup(String groupCode) {
        int indexGroup = 0;
        for (int i = 0; i < groupsCounter; i++) {
            if (groups[i].getCode().equals(groupCode)) {
                indexGroup = i;
            }
        }
        return indexGroup;
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
        public void addGrade (int studentIndex, String groupCode, double grade){
            if (!groupExists(groupCode)) {
                System.out.println("Grupa " + groupCode + " nie istnieje.");
            } else if (!studentBelongsToTheGroup(studentIndex, groupCode)) {
                    System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
                } else if (gradeExists(studentIndex, groupCode, grade)) {
                    System.out.println("Student o indeksie " + studentIndex + " ma juz wystawiona ocene dla grupy " + groupCode);
                } else {
                    Student student = findStudent(studentIndex);
                    int indexGroup = findGroup(groupCode);
                    Group group = groups[indexGroup];
                    grades[gradesCount] = new Grade(grade, student, group);
                    gradesCount++;
                }
            }

    private Student findStudent(int studentIndex) {
            int counter = 0;
        for (int i = 0; i < studentsCount; i++) {
            if (students[i].getIndex() == studentIndex) {
                counter = i;
            }
        }
        return students[counter];
    }

    private boolean gradeExists(int studentIndex, String groupCode, double grade) {
        boolean gradeCheck = false;
        for (int i = 0; i < gradesCount; i++) {
            if (grades[i].getGrade() == grade && grades[i].getStudent().getIndex() == studentIndex && grades[i].getGrade() == grade) {
                gradeCheck = true;
            }
        }
        return gradeCheck;
    }


    private boolean studentBelongsToTheGroup(int studentIndex, String groupCode) {
        boolean studentInGroupCheck = false;
        int indexGroup = findGroup(groupCode);
        for (int i = 0; i < groups[indexGroup].getStudentsCounter(); i++) {
            if (groups[indexGroup].getStudents()[i].getIndex() == studentIndex) {
                studentInGroupCheck = true;
            }
        }
        return studentInGroupCheck;
    }

    /**
         * Wyświetla wszystkie oceny studenta.
         * Przykładowy wydruk:
         * Podstawy programowania: 5.0
         * Programowanie obiektowe: 5.5
         *
         * @param index - numer indeksu studenta dla którego wyświetlić oceny
         */
        public void printGradesForStudent (int index){
            for (int i = 0; i < gradesCount; i++) {
                if (grades[i].getStudent().getIndex() == index) {
                    System.out.println(grades[i].getGroup().getName() + ": " + grades[i].getGrade());
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
        public void printGradesForGroup (String groupCode){
            for (int i = 0; i < gradesCount; i++) {
                if (grades[i].getGroup().getCode().equals(groupCode)) {
                    for (int j = 0; j < grades[i].getGroup().getStudentsCounter(); j++) {
                        System.out.println(grades[i].getGroup().getStudents()[j].getStudentInfo() + ": "
                          + getStudentGrade(grades[i].getGroup().getStudents()[j].getIndex()));
                    }
                    break;
                }
            }
        }

    private double getStudentGrade(int index) {
        double studentGrade = 0;
        for (int i = 0; i < gradesCount; i++) {
            if (grades[i].getStudent().getIndex() == index){
                studentGrade = grades[i].getGrade();
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
        public void printAllStudents () {
            for (int i = 0; i < studentsCount; i++) {
                System.out.println(students[i].getStudentInfo());
            }
        }

    }
