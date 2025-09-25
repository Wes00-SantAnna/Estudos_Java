package Estudos_Java.Lista_5.ex_1;

class Motor {
    private int potencia; 

    public Motor(int potencia) {
        this.potencia = potencia;
    }

    public int getPotencia() {
        return this.potencia;
    }

    @Override
    public String toString() {
        return "Motor com " + this.potencia + " cavalos-vapor.";
    }
}