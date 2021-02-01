import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int number1;
        int number2;
        int sum;
        double quot;

        number1 = input.nextInt();

        number2 = input.nextInt();

        sum = number1 + number2;
        quot = number1 / (double)number2;

        System.out.printf("Suma este %d%n",sum);
        System.out.printf("Rezultatul impartirii este %f%n",quot);
    }
}
