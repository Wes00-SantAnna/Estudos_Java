package Lista_6.ex_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IOExceptions {
    
    public String loadFileContent(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
            System.out.println("Arquivo lido com sucesso: " + file.getName());
            System.out.println("Tamanho do conteúdo: " + content.length() + " caracteres");
            
            return content.toString();

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            throw e;
        }
    }
    
    public static void main(String[] args) {
        IOExceptions ioExceptions = new IOExceptions();
        File file = new File("Lista_6\\ex_4\\java_linguagem.txt");
        
        try {
            String conteudo = ioExceptions.loadFileContent(file);
            
            // Agora fazemos algo útil com o conteúdo:
            System.out.println("\nCONTEÚDO DO ARQUIVO:");
            System.out.println("=" + "=".repeat(50));
            System.out.println(conteudo);
            System.out.println("=" + "=".repeat(50));
            
            // Exemplo: contar linhas
            long linhas = conteudo.lines().count();
            System.out.println("Total de linhas: " + linhas);
            
        } catch (IOException e) {
            System.out.println("Falha ao processar arquivo: " + e.getMessage());
        }
    }
}
