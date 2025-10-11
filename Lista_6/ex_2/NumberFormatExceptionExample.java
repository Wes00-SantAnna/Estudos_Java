package Lista_6.ex_2;

public class NumberFormatExceptionExample {
    
    public static void main(String[] args) {
        // Exemplos que causam NumberFormatException
        String[] testStrings = {
            "123",      // ✅ Válido
            "abc",      // ❌ Não é número
            "12.5",     // ❌ Tem ponto decimal (para parseInt)
            "",         // ❌ String vazia
            null,       // ❌ String nula
            "  123  ",  // ✅ Válido (trim automático)
            "123abc"    // ❌ Mistura números e letras
        };
        
        for (String s : testStrings) {
            convertStringToInt(s);
        }
    }
    
    public static void convertStringToInt(String myString) {
        System.out.println("\n--- Testando: \"" + myString + "\" ---");
        
        try {
            // Verificar se é null antes de tentar converter
            if (myString == null) {
                throw new NumberFormatException("String não pode ser null");
            }
            
            // Tentar converter String para int
            int i = Integer.parseInt(myString);
            System.out.println("✅ Sucesso! Número convertido: " + i);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro de conversão!");
            System.out.println("Motivo: " + e.getMessage());
            
            // Tratamento alternativo - você decide o que fazer
            System.out.println("Usando valor padrão: 0");
            int i = 0; // ou qualquer lógica de fallback
        }
    }
}