package Lista_5.ex_9_e_10;

public class Carro extends Veiculo {
    public Carro(String modelo) {
        super(modelo);
    }

    @Override
    public void mover() {
        System.out.println(modelo + " está andando na estrada.");
    }

    public void ligarArCondicionado() {
        System.out.println(modelo + " ligou o ar-condicionado.");
    }
}
