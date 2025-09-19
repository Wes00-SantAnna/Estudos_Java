public class TestaConta {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(12345, 4321);

        conta.depositar(500, 4321); // senha correta
        conta.depositar(200, 1111); // senha errada

        conta.sacar(100, 4321); // saque válido
        conta.sacar(1000, 4321); // saque maior que o saldo
        conta.sacar(50, 1111); // senha incorreta

        System.out.println("Saldo final: " + conta.getSaldo(4321));
        conta.getSaldo(9999); // senha errada
    }
}
