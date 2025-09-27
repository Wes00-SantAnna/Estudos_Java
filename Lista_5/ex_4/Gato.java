package Lista_5.ex_4;

public class Gato extends Animal {
    public Gato(String nome, int idade) {
        super(nome, idade);
    }

    @Override
    public void fazerSom() {
        System.out.println(nome + " diz: Miau!");
    }

    public void arranharMoveis() {
        System.out.println(nome + " está arranhando o sofá!");
    }
}
