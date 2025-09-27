package Lista_5.ex_7_e_8;

public class Gerente extends Funcionario {
    public Gerente(String nome) {
        super(nome);
    }

    @Override
    public void trabalhar() {
        System.out.println(nome + " está gerenciando a equipe.");
    }

    public void fazerReuniao() {
        System.out.println(nome + " está conduzindo uma reunião.");
    }
}