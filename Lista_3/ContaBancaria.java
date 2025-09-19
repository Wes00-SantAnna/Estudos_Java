public class ContaBancaria {
    private int numeroConta;
    private float saldo;
    private int senha;

    // Construtor
    public ContaBancaria(int numeroConta, int senha) {
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = 0.0f; // saldo inicial
    }

    public void depositar(float valor, int senha) {
        if (senha == this.senha) {
            if (valor > 0) {
                this.saldo += valor;
                System.out.println("Depósito realizado com sucesso!");
            } else {
                System.out.println("Valor inválido para depósito.");
            }
        } else {
            System.out.println("Senha incorreta. Depósito não realizado.");
        }
    }

    public boolean sacar(float valor, int senha) {
        if (senha == this.senha) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.println("Saque realizado com sucesso!");
                return true;
            } else {
                System.out.println("Saldo insuficiente ou valor inválido.");
                return false;
            }
        } else {
            System.out.println("Senha incorreta. Saque não realizado.");
            return false;
        }
    }

    public float getSaldo(int senha) {
        if (senha == this.senha) {
            return saldo;
        } else {
            System.out.println("Senha incorreta. Não foi possível consultar o saldo.");
            return -1; // indica falha
        }
    }

    public int getNumeroConta() {
        return numeroConta;
    }
}
