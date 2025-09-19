package Encap_do_Construtor;

public class Conta {
    private int num_conta;
    private float saldo;

    public Conta(float saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void setConta(int num_conta) {
        this.num_conta = num_conta;
    }

    public void depositar(float valor) {
        this.saldo += valor;
    }

    public boolean sacar(float valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public float getSaldo() {
        return saldo;
    }

    public int getNumConta() {
        return num_conta;
    }
}