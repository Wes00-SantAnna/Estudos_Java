package Lista_5.ex_4;

public abstract class Animal {
    protected String nome;
    protected int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public void mostrarInfo() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + " anos");
    }

    public abstract void fazerSom();
}
