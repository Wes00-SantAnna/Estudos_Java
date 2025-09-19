package mod_acesso.pacote1;

public class Acesso {

    private String privado = "Privado";
    String padrao = "Padrao (package-private)";
    protected String protegido = "Protegido";
    public String publico = "Publico";

    private String metodoPrivado() {
        return "Metodo Privado";
    }

    String metodoPadrao() {
        return "Metodo Padrao";
    }

    protected String metodoProtegido() {
        return "Metodo Protegido";
    }

    public String metodoPublico() {
        return "Metodo Publico";
    }
}