import java.util.ArrayList;
import java.util.Scanner;

// Student class
class Student {
    private String name;
    private String studentId;
    private ArrayList<Double> grades;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.grades = new ArrayList<>();
    }

    // Add grade to the student
    public void addGrade(double grade) {
        grades.add(grade);
    }

    // Calculate average grade
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    // Display individual grades
    public void displayGrades() {
        System.out.println("Grades for " + name + ": ");
        for (double grade : grades) {
            System.out.println(grade);
        }
    }

    // Determine if the student has passed
    public boolean hasPassed(double passingGrade) {
        double average = calculateAverage();
        return average >= passingGrade;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}

// Class to handle multiple students and class average
class SchoolClass {
    private ArrayList<Student> students;
    private double passingGrade;

    public SchoolClass(double passingGrade) {
        this.students = new ArrayList<>();
        this.passingGrade = passingGrade;
    }

    // Add a new student to the class
    public void addStudent(Student student) {
        students.add(student);
    }

    // Calculate the class average
    public double calculateClassAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        double totalSum = 0;
        int totalGrades = 0;
        for (Student student : students) {
            totalSum += student.calculateAverage();
            totalGrades++;
        }
        return totalSum / totalGrades;
    }

    // Display all students and their results
    public void displayClassResults() {
        for (Student student : students) {
            System.out.println("Student: " + student.getName() + " (ID: " + student.getStudentId() + ")");
            student.displayGrades();
            System.out.println("Average Grade: " + student.calculateAverage());
            if (student.hasPassed(passingGrade)) {
                System.out.println("Status: Passed");
            } else {
                System.out.println("Status: Failed");
            }
            System.out.println();
        }
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolClass schoolClass = new SchoolClass(50.0); // 50 is the passing grade

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();

        for (int i = 0; i < numberOfStudents; i++) {
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student's ID: ");
            String studentId = scanner.nextLine();

            Student student = new Student(name, studentId);
            System.out.print("Enter number of grades for " + name + ": ");
            int numberOfGrades = scanner.nextInt();

            for (int j = 0; j < numberOfGrades; j++) {
                System.out.print("Enter grade " + (j + 1) + ": ");
                double grade = scanner.nextDouble();
                student.addGrade(grade);
            }

            schoolClass.addStudent(student);
        }

        // Display class results
        System.out.println("\nClass Results:");
        schoolClass.displayClassResults();

        // Calculate and display class average
        double classAverage = schoolClass.calculateClassAverage();
        System.out.println("Class Average: " + classAverage);
    }
}
