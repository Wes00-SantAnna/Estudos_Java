package Lista_6.ex_3;

public class ArrayIndexOutOfBoundsExample {

    public static void printName(String[] names, int index) throws ArrayIndexOutOfBoundsException {
        // Tenta acessar um índice específico que pode estar fora dos limites
        System.out.println("Acessando índice " + index + ": " + names[index]);
    }
    
    public static void printAllNames(String[] names) {
        // Versão segura que imprime todos os nomes
        for (int i = 0; i < names.length; i++) {
            System.out.println("Índice " + i + ": " + names[i]); 
        }
    }

    public static void main(String[] args) {
        String[] names = {"wesley", "andre"};
        
        System.out.println("=== Exemplo 1: Acesso seguro ===");
        printAllNames(names);

        System.out.println("\n=== Exemplo 2: Testando índices ===");
        
        // Testando vários índices
        int[] testIndexes = {0, 1, 2, -1, 5};
        
        for (int index : testIndexes) {
            try {
                printName(names, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("❌ Erro: Índice " + index + " está fora do limite do array!");
                System.out.println("   Array tem tamanho " + names.length + " (índices válidos: 0 a " + (names.length-1) + ")");
            }
        }
    }
}
