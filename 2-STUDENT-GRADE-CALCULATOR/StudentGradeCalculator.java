import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSubjects = 0;

        System.out.print("Enter the number of subjects: ");
        while (!scanner.hasNextInt() || (numSubjects = scanner.nextInt()) < 0) {
            System.out.println("Invalid input. Please enter an integer above 0");
            scanner.nextLine();
            System.out.print("Enter the number of subjects: ");
        }
        scanner.nextLine();
        double totalMarks = 0;
        double marks = 0;

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + ": ");
            while (!scanner.hasNextDouble() || (marks = scanner.nextDouble()) < 0 || marks > 100) {
                 System.out.println("Invalid input. Please enter value between 0 to 100!!");
                 scanner.nextLine();
                 System.out.print("Enter marks obtained in subject " + i + ": ");
            }
            scanner.nextLine();
            totalMarks += marks;
        }

        double averagePercentage = totalMarks / (numSubjects * 100) * 100;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("----------------------------");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}


