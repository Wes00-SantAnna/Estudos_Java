
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReadArchive extends JFrame implements MenuHandler {
    private JLabel statusLabel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private MenuBarCreator menuCreator;
    private File currentFile;
    
    public ReadArchive() {
        initializeComponents();
        setupFrame();
    }
    
    private void initializeComponents() {
        // Área de texto para exibir conteúdo dos arquivos
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE); 
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        scrollPane = new JScrollPane(textArea);
        
        // Label de status
        statusLabel = new JLabel("Pronto - Nenhum arquivo carregado");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // Criar menu
        menuCreator = new MenuBarCreator(this);
        this.setJMenuBar(menuCreator.getMenuBar());
    }
    
    private void setupFrame() {
        this.setTitle("Basic GUI with Threads");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Layout
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(statusLabel, BorderLayout.SOUTH);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    // Método requerido pelo MenuBarCreator
    @Override
    public void openFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        
        // Configurar o diálogo
        fileChooser.setDialogTitle("Selecionar Arquivo de Texto");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Filtro para arquivos de texto
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Arquivos de Texto (*.txt, *.java, *.log, *.md)", 
            "txt", "java", "log", "md"
        );
        fileChooser.setFileFilter(filter);
        
        // Mostrar o diálogo
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadFileContent(selectedFile);
        }
    }
    
    private void loadFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
            // Exibir conteúdo na área de texto
            textArea.setText(content.toString());
            currentFile = file;
            
            // Atualizar interface
            statusLabel.setText("Arquivo carregado: " + file.getName());
            this.setTitle("Basic GUI with Threads - " + file.getName());
            
            System.out.println("Arquivo aberto: " + file.getAbsolutePath());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao ler o arquivo:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void closeFileAction() {
        if (currentFile != null) {
            // Confirmar se quer fechar
            int option = JOptionPane.showConfirmDialog(
                this,
                "Fechar o arquivo '" + currentFile.getName() + "'?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );
            
            if (option == JOptionPane.YES_OPTION) {
                // Limpar a área de texto
                textArea.setText("");
                currentFile = null;
                
                // Atualizar interface
                statusLabel.setText("Pronto - Nenhum arquivo carregado");
                this.setTitle("Basic GUI with Threads");
                
                System.out.println("Arquivo fechado");
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Nenhum arquivo está aberto no momento.",
                "Informação",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    // ===== OUTROS MÉTODOS DO MENU =====
    @Override
    public void exitApplication() {
        int option = JOptionPane.showConfirmDialog(
            this, 
            "Tem certeza que deseja sair?", 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    @Override
    public void showPatternsDialog() {
        JOptionPane.showMessageDialog(this, "Configurar Padrões - Em desenvolvimento");
    }
    
    @Override
    public void showColorsDialog() {
        JOptionPane.showMessageDialog(this, "Configurar Cores - Em desenvolvimento");
    }

    @Override
    public void showSpeedDialog() {
        JOptionPane.showMessageDialog(this, "Configurar Velocidade - Em desenvolvimento");
    }
    
    @Override
    public void showHelpDialog() {
        JOptionPane.showMessageDialog(this, "Ajuda - Em desenvolvimento");
    }
    
    @Override
    public void showAboutDialog() {
        JOptionPane.showMessageDialog(
            this,
            "Basic GUI with Threads\nVersão 1.0\nDesenvolvido por Grupo 03",
            "Sobre",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReadArchive::new);
    }
}
