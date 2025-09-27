public class Moto extends Veiculo {
    public Moto(String modelo) {
        super(modelo);
    }

    @Override
    public void mover() {
        System.out.println(modelo + " está acelerando entre os carros.");
    }

    public void empinar() {
        System.out.println(modelo + " está empinando a moto!");
    }
}