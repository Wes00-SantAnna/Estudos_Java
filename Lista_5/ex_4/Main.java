package Lista_5.ex_4;

public class Main {
    public static void main(String[] args) {
        // Criando objetos
        Cachorro dog = new Cachorro("Rex", 5);
        Gato cat = new Gato("Mimi", 3);

        // Usando métodos herdados
        dog.mostrarInfo();
        dog.fazerSom();
        dog.buscarBolinha();

        System.out.println();

        cat.mostrarInfo();
        cat.fazerSom();
        cat.arranharMoveis();
    }
}
