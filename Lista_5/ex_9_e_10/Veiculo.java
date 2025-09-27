package Lista_5.ex_9_e_10;

public abstract class Veiculo {
    protected String modelo;

    public Veiculo(String modelo) {
        this.modelo = modelo;
    }

    public abstract void mover();
}