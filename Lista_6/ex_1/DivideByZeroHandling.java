package Lista_6.ex_1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideByZeroHandling {

    public static int quotient(int numerator, int denominator) throws ArithmeticException {
        return numerator / denominator;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite dois valores: ");

            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                int result = quotient(x, y);
                System.out.println("Resultado: " + result);

            } catch (ArithmeticException e) {
                System.out.println("Erro: Divisão por zero!");
                System.out.println("O denominador deve ser um número diferente de zero");

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida!");
                System.out.println("Digite apenas números inteiros");
            }
        }
    }
}
