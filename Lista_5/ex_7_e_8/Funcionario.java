package Lista_5.ex_7_e_8;

public abstract class Funcionario {
    protected String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public abstract void trabalhar();
}
