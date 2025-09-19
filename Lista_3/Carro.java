public class Carro {
    public String marca;
    public String modelo;
    public int ano;
    public boolean motorLigado;
    public double velocidade;

    // Construtor padrão
    public Carro() {
        this.marca = "Desconhecida";
        this.modelo = "Desconhecida";
        this.ano = 0;
        this.motorLigado = false;
        this.velocidade = 0;
    }

    // Construtor completo
    public Carro(String marca, String modelo, int ano, boolean motorLigado, double velocidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.motorLigado = motorLigado;
        this.velocidade = velocidade;
    }

    // Métodos ligar/desligar motor
    public boolean ligarMotor() {
        this.motorLigado = true;
        return motorLigado;
    }

    public boolean desligarMotor() {
        this.motorLigado = false;
        this.velocidade = 0;
        return motorLigado;
    }

    //  Sobrecarga de métodos acelerar
    public double acelerar() {
        return acelerar(10); 
    }

    public double acelerar(double valor) {
        if (motorLigado) {
            this.velocidade += valor;
        } else {
            System.out.println("Não é possível acelerar: motor desligado.");
        }
        return velocidade;
    }

    //  Sobrecarga de métodos frear
    public double frear() {
        return frear(10);
    }

    public double frear(double valor) {
        this.velocidade -= valor;
        if (velocidade < 0) velocidade = 0;
        return velocidade;
    }

    @Override
    public String toString() {
        return "Marca: " + marca +
               "\nModelo: " + modelo +
               "\nAno: " + ano +
               "\nMotor: " + (motorLigado ? "Ligado" : "Desligado") +
               "\nVelocidade: " + velocidade + " km/h";
    }
}
