import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt the user to enter a number.
        System.out.print("Enter a number: ");
        int number1 = scanner.nextInt();

        // Step 2: Prompt the user to enter another number to multiply with the first number.
        System.out.print("Enter another number to multiply with the first number: ");
        int number2 = scanner.nextInt();

        // Step 3: Perform the multiplication of the two numbers.
        int result = number1 * number2;

        // Step 4: Display the result of the multiplication to the user.
        System.out.println("Result of multiplication: " + result);

        scanner.close();
    }
}
