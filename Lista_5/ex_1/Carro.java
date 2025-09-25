package Estudos_Java.Lista_5.ex_1;

class Carro {
    private Motor motor; 

    public Carro(Motor motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Carro com " + motor.toString();
    }
}