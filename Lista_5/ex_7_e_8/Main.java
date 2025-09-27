package Lista_5.ex_7_e_8;

public class Main {
    public static void main(String[] args) {
        Funcionario[] funcionarios = {
            new Gerente("Alice"),
            new Desenvolvedor("Bob"),
            new Gerente("Carlos")
        };

        for (Funcionario f : funcionarios) {
            f.trabalhar();

            if (f instanceof Gerente) {
                Gerente g = (Gerente) f; // casting
                g.fazerReuniao();
            } else if (f instanceof Desenvolvedor) {
                Desenvolvedor d = (Desenvolvedor) f; // casting
                d.escreverCodigo();
            }
            System.out.println();
        }
    }
}
