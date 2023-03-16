package pl.javastart.task;

public class Student {
    private int index;
    private String firstName;
    private String lastName;
    private static int indexCounter;
    private static int[] indexArray = new int[1];

    public Student(int index, String firstName, String lastName) {
        indexCounter++;
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        addIndexToArray(index);

        System.out.println("tworze nowego studenta " + indexCounter);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static int getIndexCounter() {
        return indexCounter;
    }

    public static int[] getIndexArray() {
        return indexArray;
    }

    private void addIndexToArray(int index) {
        if (indexCounter == 1) {
            indexArray[0] = index;
        }
        if ((indexCounter > 1) && (indexIsUnique())) {
            int[] temp = new int[indexArray.length + 1];
            for (int i = 0; i < indexArray.length; i++) {
                temp[i] = indexArray[i];
            }
            temp[indexArray.length] = index;
            indexArray = temp;
        }
    }

    private boolean indexIsUnique() {
        boolean indexUniqueCheck = true;
        for (int i = 0; i < indexArray.length; i++) {
            if (indexArray[i] == index) {
                indexUniqueCheck = false;
        }
            }
        return indexUniqueCheck;
    }

    public void printStudent () {
        System.out.println(index + " " + firstName + " " + lastName);
    }
}
