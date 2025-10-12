package ex_5;

public class MyCustomException {

    // método estático para facilitar chamada a partir do main
    public static void sumAndPrint(int x, int y) throws NumeroNegativoException {
        if (x < 0 || y < 0) {
            throw new NumeroNegativoException("Números negativos não são permitidos");
        }
        System.out.println("Soma: " + (x + y));
    }

    // Exceção personalizada como nested static class para uso simples neste arquivo
    public static class NumeroNegativoException extends Exception /* ou RuntimeException */ {

        /**
         * importante caso a exceção seja serializada
         */
        private static final long serialVersionUID = 1149241039409861914L;

        // constrói um objeto NumeroNegativoException com a mensagem passada por parâmetro
        public NumeroNegativoException(String msg) {
            super(msg);
        }

        // constrói um objeto NumeroNegativoException com mensagem e a causa dessa exceção
        public NumeroNegativoException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static void main(String[] args) {
        int x = 10;
        int y = -3;
        try {
            sumAndPrint(x, y);
        } catch (NumeroNegativoException e) {
            System.err.println("Erro: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}
