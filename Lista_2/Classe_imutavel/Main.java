package Classe_imutavel;

public class Main {
    public static void main(String[] args) {
        Ponto2D ponto = new Ponto2D(2.5f, 3.5f);

        System.out.println("As coordenadas sao:" + "(" + (ponto.getX()) + ";" + (ponto.getY()) + ")");
    }
}