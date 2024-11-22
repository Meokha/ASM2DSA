import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StudentManagement {
    private List<Student> students;
    private Scanner scanner;

    public StudentManagement() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        String id;
        String name;
        double score;
        while (true) {
            try {
                System.out.print("ID Student (numeric only): ");
                id = scanner.nextLine();
                if (id.matches("\\d+")) { // Regular expression for numeric characters
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid ID. Please enter numeric characters only.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Name Student (alphabetic only): ");
                name = scanner.nextLine();
                if (name.matches("[a-zA-Z ]+")) { // Regular expression for letters and spaces
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid Name. Please enter alphabetic characters only.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Score Student (0 to 10): ");
                score = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (score >= 0 && score <= 10) {
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid Score. Please enter a number between 0 and 10.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        students.add(new Student(id, name, score));
        System.out.println("Add Student successfully.");
    }

    public void editStudent() {
        try {
            System.out.print("Enter the ID of the student to edit (numeric only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid ID. Please enter numeric characters only.");
            }
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    // Validate new name (alphabetic only)
                    String newName;
                    while (true) {
                        try {
                            System.out.print("New Name (alphabetic only): ");
                            newName = scanner.nextLine();
                            if (newName.matches("[a-zA-Z ]+")) {
                                break;
                            } else {
                                throw new IllegalArgumentException("Invalid Name. Please enter alphabetic characters only.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    double newScore;
                    while (true) {
                        try {
                            System.out.print("New Score (0 to 10): ");
                            newScore = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            if (newScore >= 0 && newScore <= 10) {
                                break;
                            } else {
                                throw new IllegalArgumentException("Invalid Score. Please enter a number between 0 and 10.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear invalid input
                        }
                    }
                    students.remove(student);
                    students.add(new Student(id, newName, newScore));
                    System.out.println("Update Student successfully.");
                    return;
                }
            }
            System.out.println("Student ID not found: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while editing student: " + e.getMessage());
            scanner.nextLine(); // Clear invalid input
        }
    }

    public void deleteStudent() {
        try {
            System.out.print("Enter the student ID to delete (numbers only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid ID. Please enter numeric characters only.");
            }
            boolean removed = students.removeIf(student -> student.getId().equals(id));
            if (removed) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student ID not found: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear invalid input
        }
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("Student ID not found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void bubbleSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    // Swap
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted using Bubble Sort.");
    }

    // Selection Sort algorithm
    public void selectionSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getScore() > students.get(maxIdx).getScore()) {
                    maxIdx = j;
                }
            }
            // Swap
            Student temp = students.get(maxIdx);
            students.set(maxIdx, students.get(i));
            students.set(i, temp);
        }
        System.out.println("Students sorted using Selection Sort.");
    }

    public void sortStudents() {
        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        long startTime, endTime;

        switch (choice) {
            case 1:
                startTime = System.nanoTime();
                bubbleSort();
                endTime = System.nanoTime();
                System.out.println("Bubble Sort took " + (endTime - startTime) + " nanoseconds");
                break;
            case 2:
                startTime = System.nanoTime();
                selectionSort();
                endTime = System.nanoTime();
                System.out.println("Selection Sort took " + (endTime - startTime) + " nanoseconds");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("The list of students has been arranged by score.");
        listStudents();
    }

    public void searchStudent() {
        try {
            System.out.print("Enter the student ID you are looking for (numbers only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid ID. Please enter numeric characters only.");
            }
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    System.out.println(student);
                    return;
                }
            }
            System.out.println("Student ID not found: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear invalid input
        }
    }
    public void run() {
        while (true) {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Add");
                System.out.println("2. Edit");
                System.out.println("3. Delete");
                System.out.println("4. List of students");
                System.out.println("5. Arrange students");
                System.out.println("6. Seachr");
                System.out.println("0. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        editStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        listStudents();
                        break;
                    case 5:
                        sortStudents();
                        break;
                    case 6:
                        searchStudent();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }} catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
