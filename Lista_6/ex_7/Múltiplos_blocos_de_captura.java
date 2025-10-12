package ex_7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Múltiplos_blocos_de_captura {

    public static void main(String[] args) {
        int[] numbers = new int[3];

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite valores inteiros. Para parar, digite 'sair' ou pressione Ctrl+C.");

            int count = 0;

            while (true) {
                System.out.print("Insira um valor (ou digite 'ok' para encerrar): ");
                String token = scanner.next();

                if (token.equalsIgnoreCase("ok") || token.equalsIgnoreCase("sair")) {
                    break;
                }

                try {
                    int value = Integer.parseInt(token);

                    numbers[count] = value;
                    System.out.println("Valor inserido na posição " + count + ": " + value);
                    count++;

                } catch (NumberFormatException e) {
                    System.err.println("Entrada inválida: digite somente números inteiros. (Recebido: '" + token + "')");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Erro: você tentou inserir mais valores do que o permitido no array (tamanho = " + numbers.length + ").");
                    System.out.print("Conteúdo atual do array: ");
                    for (int i = 0; i < numbers.length; i++) {
                        System.out.print(numbers[i] + (i < numbers.length - 1 ? ", " : "\n"));
                    }
                    break;
                }
            }

            System.out.println("Execução finalizada. Valores inseridos:");
            for (int i = 0; i < numbers.length; i++) {
                System.out.println("pos[" + i + "] = " + numbers[i]);
            }

        } catch (InputMismatchException e) {
            System.err.println("Erro de leitura: entrada incompatível.");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
