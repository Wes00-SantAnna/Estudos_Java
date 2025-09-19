import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<BasketballPlayer> players = new ArrayList<>();
        
            System.out.println("Digite os dados de 20 jogadores:");
        
            for (int i = 1; i <= 20; i++) {
                System.out.print("Nome do jogador " + i + ": ");
                String name = sc.nextLine();
        
                System.out.print("Altura do jogador " + i + " (em metros): ");
                double height = sc.nextDouble();
                sc.nextLine();
        
                players.add(new BasketballPlayer(name, height));
            }
        
            Collections.sort(players);
        
            System.out.println("\nJogadores em ordem crescente de altura:");
            for (BasketballPlayer p : players) {
                System.out.println(p);
            }
        }
    }
}
