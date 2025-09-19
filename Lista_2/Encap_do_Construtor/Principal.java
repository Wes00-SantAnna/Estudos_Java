package Encap_do_Construtor;

public class Principal {
    public static void main(String[] args) {
        Conta dados = new Conta(7.7f);
        dados.setConta(4023);

        dados.depositar(100.0f);
        boolean saque = dados.sacar(50.0f);

        System.out.println("Numero da conta: " + dados.getNumConta());
        System.out.println("Saldo atual: " + dados.getSaldo());
        System.out.println("Saque realizado: " + (saque ? "Sim" : "Nao"));
    }
}     
