public class Principal {
    public static void main(String[] args) {
        Dados dados = new Dados();

        dados.setNome("Wesley");
        dados.setIdade(21); 

        String x = dados.getNome();
        int y = dados.getIdade();

        System.out.println("Nome: " + x + "Idade: " + y);
    }
}
