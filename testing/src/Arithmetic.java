import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int number1;
        int number2;
        int sum;
        int dif;

        number1 = input.nextInt();

        number2 = input.nextInt();

        sum = number1 + number2;
        dif = number1 - number2;

        System.out.printf("Suma este %d%n",sum);
        System.out.printf("Diferenta este %d%n",dif);
    }
}
