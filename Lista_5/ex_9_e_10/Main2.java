package Lista_5.ex_9_e_10;

public class Main2 {
    public static void main(String[] args) {
        Veiculo[] veiculos = {
            new Carro("Fusca"),
            new Caminhao("Volvo FH"),
            new Motocicleta("Hornet 600")
        };

        for (Veiculo v : veiculos) {
            v.mover();
        }
    }
}