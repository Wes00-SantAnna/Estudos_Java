package Lista_5.ex_9_e_10;

public class Caminhao extends Veiculo {
    public Caminhao(String modelo) {
        super(modelo);
    }

    @Override
    public void mover() {
        System.out.println(modelo + " está transportando carga.");
    }

    public void descarregar() {
        System.out.println(modelo + " descarregou a carga.");
    }
}