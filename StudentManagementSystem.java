import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student student = new Student(nextId++, name, marks);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\nList of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found with ID: " + id);
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new marks (enter -1 to keep current): ");
        double marks = scanner.nextDouble();
        if (marks != -1) {
            student.setMarks(marks);
        }
        scanner.nextLine(); // Consume newline

        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found with ID: " + id);
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}