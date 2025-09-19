import java.util.ArrayList;

public class TestaCarro {
    public static void main(String[] args) {
        ArrayList<Carro> carros = new ArrayList<>();

        // Criando alguns carros
        carros.add(new Carro("Fiat", "Uno", 2010, false, 0));
        carros.add(new Carro("Volkswagen", "Gol", 2015, false, 0));
        carros.add(new Carro("Toyota", "Corolla", 2020, false, 0));

        // Exercitando métodos
        for (Carro carro : carros) {
            System.out.println("\n=== Novo Carro ===");
            System.out.println(carro);

            // Liga motor
            carro.ligarMotor();

            // Acelera com valor padrão
            carro.acelerar();
            System.out.println("Após acelerar padrão: " + carro.velocidade + " km/h");

            // Acelera com valor especificado
            carro.acelerar(30);
            System.out.println("Após acelerar +30 km/h: " + carro.velocidade + " km/h");

            // Freia com valor padrão
            carro.frear();
            System.out.println("Após frear padrão: " + carro.velocidade + " km/h");

            // Freia com valor especificado
            carro.frear(15);
            System.out.println("Após frear 15 km/h: " + carro.velocidade + " km/h");

            // Desliga motor
            carro.desligarMotor();
            System.out.println("Após desligar motor:");
            System.out.println(carro);
        }
    }
}
