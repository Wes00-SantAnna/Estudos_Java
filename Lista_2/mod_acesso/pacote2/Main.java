package mod_acesso.pacote2;

import mod_acesso.pacote1.Acesso;

public class Main {
    public static void main(String[] args) {
        Acesso obj = new Acesso();

        // System.out.println(obj.privado); Erro: private
        // System.out.println(obj.padrao); Erro: package-private
        // System.out.println(obj.protegido); Erro: protected (fora do pacote e sem heranca)
        System.out.println(obj.publico);

        // // System.out.println(obj.metodoPrivado()); Erro: private
        // // System.out.println(obj.metodoPadrao()); Erro: package-private
        // // System.out.println(obj.metodoProtegido Erro: protected
        System.out.println(obj.metodoPublico());
        
    }
}