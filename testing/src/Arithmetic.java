import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int number1;
        int number2;
        int sum;
        int dif;

        System.out.print("Introduceti primul numar: ");
        number1 = input.nextInt();

        System.out.print("Introduceti al doilea numar: ");
        number2 = input.nextInt();

        sum = number1 + number2;
        dif = number1 - number2;

        System.out.printf("Suma este %d%n",sum);
        // suma numerelor
        System.out.printf("Diferenta este %d%n",dif);
    }
}
