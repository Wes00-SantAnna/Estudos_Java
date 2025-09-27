package Lista_5.ex_5_e_6;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animais = new ArrayList<>();

        animais.add(new Cachorro("Rex", 4));
        animais.add(new Gato("Mimi", 2));
        animais.add(new Cachorro("Bolt", 6));
        animais.add(new Gato("Luna", 1));

        // Polimorfismo: mesma chamada, comportamentos diferentes
        for (Animal a : animais) {
            a.mostrarInfo();
            a.emitirSom();
            System.out.println();
        }
    }
}
