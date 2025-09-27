package Lista_5.ex_7_e_8;

public class Desenvolvedor extends Funcionario {
    public Desenvolvedor(String nome) {
        super(nome);
    }

    @Override
    public void trabalhar() {
        System.out.println(nome + " está programando.");
    }

    public void escreverCodigo() {
        System.out.println(nome + " está escrevendo código Java.");
    }
}