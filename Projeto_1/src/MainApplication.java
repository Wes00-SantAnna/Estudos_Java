import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class MainApplication extends JFrame implements MenuHandler {
    private JLabel statusLabel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private MenuBarCreator menuCreator;
    private File currentFile;
    
    // NOVOS COMPONENTES PARA THREADS
    private ThreadsHandler threadsHandler;
    private JLayeredPane layeredPane;
    
    public MainApplication() {
        initializeComponents();
        setupFrame();
        
        // Iniciar animação automaticamente
        threadsHandler.startAnimation();
    }
    
    private void initializeComponents() {
        // Área de texto para exibir conteúdo dos arquivos
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // MODIFICAÇÃO: Configurar cores do texto
        textArea.setForeground(new Color(255,255, 255)); 
        
        // MODIFICAÇÃO: Tornar componentes transparentes para ver a animação
        textArea.setOpaque(false);
        textArea.setBackground(new Color(255, 255, 255, 230)); // Fundo semi-transparente
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Label de status
        statusLabel = new JLabel("Bem-vindo ao Basic GUI with Threads - Animação ativa");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(50, 50, 50));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // NOVO: Inicializar ThreadsHandler
        threadsHandler = new ThreadsHandler();
        
        // Criar menu
        menuCreator = new MenuBarCreator(this);
        this.setJMenuBar(menuCreator.getMenuBar());
        
        // NOVO: Configurar LayeredPane
        setupLayeredPane();
    }
    
    // NOVO MÉTODO: Configurar camadas para sobreposição
    private void setupLayeredPane() {
        layeredPane = new JLayeredPane();
        
        // Adicionar ThreadsHandler no fundo
        layeredPane.add(threadsHandler, JLayeredPane.DEFAULT_LAYER);
        
        // Adicionar ScrollPane com texto no topo
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);
    }
    
    private void setupFrame() {
        this.setTitle("Basic GUI with Threads");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // MODIFICAÇÃO: Layout com LayeredPane
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.SOUTH);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        // NOVO: Listener para redimensionamento
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateComponentSizes();
            }
        });
        
        // Atualizar tamanhos iniciais
        updateComponentSizes();
    }
    
    // NOVO MÉTODO: Atualizar tamanhos quando redimensionar
    private void updateComponentSizes() {
        if (layeredPane != null && threadsHandler != null) {
            Dimension size = layeredPane.getSize();
            
            // ThreadsHandler ocupa toda a área
            threadsHandler.setBounds(0, 0, size.width, size.height);
            
            // ScrollPane com margem
            int margin = 20;
            scrollPane.setBounds(margin, margin, 
                               size.width - 2*margin, 
                               size.height - 2*margin);
        }
    }
    
    // ===== MÉTODOS CHAMADOS PELO MENU (sem alteração) =====
    @Override
    public void openFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Selecionar Arquivo de Texto");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Arquivos de Texto (*.txt, *.java, *.log, *.md)", 
            "txt", "java", "log", "md"
        );
        fileChooser.setFileFilter(filter);
        
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
            
            textArea.setText(content.toString());
            currentFile = file;
            
            // MODIFICAÇÃO: Atualizar status mencionando threads
            statusLabel.setText("Arquivo carregado: " + file.getName() + " | Threads ativas");
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
            int option = JOptionPane.showConfirmDialog(
                this,
                "Fechar o arquivo '" + currentFile.getName() + "'?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );
            
            if (option == JOptionPane.YES_OPTION) {
                textArea.setText("");
                currentFile = null;
                
                statusLabel.setText("Bem-vindo ao Basic GUI with Threads - Animação ativa");
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
    
    @Override
    public void exitApplication() {
        // NOVO: Parar threads antes de sair
        if (threadsHandler != null) {
            threadsHandler.stopAnimation();
        }
        
        int option = JOptionPane.showConfirmDialog(
            this, 
            "Tem certeza que deseja sair?", 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            // Se cancelar, reiniciar animação
            if (threadsHandler != null) {
                threadsHandler.startAnimation();
            }
        }
    }
    
    // MODIFICAÇÕES: Implementar funcionalidades das threads
    @Override
    public void showPatternsDialog() {
        String[] patterns = {"CIRCLES", "SQUARES", "LINES", "PARTICLES"};
        String selected = (String) JOptionPane.showInputDialog(
            this,
            "Escolha o padrão de animação:",
            "Configurar Padrões",
            JOptionPane.QUESTION_MESSAGE,
            null,
            patterns,
            "CIRCLES"
        );
        
        if (selected != null && threadsHandler != null) {
            threadsHandler.setAnimationPattern(selected);
            statusLabel.setText("Padrão de animação alterado: " + selected);
            System.out.println("Padrão alterado para: " + selected);
        }
    }
    
    @Override
    public void showColorsDialog() {
        if (threadsHandler != null) {
            Color primaryColor = JColorChooser.showDialog(
                this, 
                "Escolha a cor primária da animação", 
                Color.BLUE
            );
            
            if (primaryColor != null) {
                Color secondaryColor = JColorChooser.showDialog(
                    this, 
                    "Escolha a cor secundária da animação", 
                    Color.CYAN
                );
                
                if (secondaryColor != null) {
                    threadsHandler.setColors(primaryColor, secondaryColor);
                    statusLabel.setText("Cores da animação atualizadas");
                    System.out.println("Cores da animação atualizadas");
                }
            }
        }
    }
    
    @Override
    public void showSpeedDialog() {
        String speedStr = JOptionPane.showInputDialog(
            this,
            "Digite a velocidade da animação em milissegundos (10-200):",
            "Configurar Velocidade",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (speedStr != null && !speedStr.trim().isEmpty()) {
            try {
                int speed = Integer.parseInt(speedStr.trim());
                if (speed >= 10 && speed <= 200 && threadsHandler != null) {
                    threadsHandler.setAnimationSpeed(speed);
                    statusLabel.setText("Velocidade da animação: " + speed + "ms");
                    System.out.println("Velocidade alterada para: " + speed + "ms");
                } else {
                    JOptionPane.showMessageDialog(
                        this, 
                        "Velocidade deve estar entre 10 e 200 milissegundos.",
                        "Valor Inválido",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Por favor, digite apenas números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    @Override
    public void showHelpDialog() {
        String helpText = """
            === BASIC GUI WITH THREADS ===
            
            FUNCIONALIDADES PRINCIPAIS:
            
            📁 ARQUIVO:
            • Abrir Arquivo: Carrega arquivos de texto (.txt, .java, .log, .md)
            • Fechar Arquivo: Remove o arquivo da tela
            • Sair: Encerra a aplicação
            
            ⚙️ CONFIGURAÇÃO (THREADS):
            • Padrões: Altera o tipo de animação de fundo
              - CIRCLES: Círculos em movimento
              - SQUARES: Quadrados rotacionando
              - LINES: Linhas com movimento angular
              - PARTICLES: Partículas flutuantes
            
            • Cores: Personaliza cores da animação
            • Velocidade: Ajusta velocidade (10-200ms)
            
            🧵 DEMONSTRAÇÃO DE THREADS:
            • Thread Principal (EDT): Interface do usuário
            • Thread de Animação: Cálculos de movimento em background
            • Timer de Repaint: Atualização visual (60 FPS)
            
            💡 DICA: A animação continua rodando mesmo com arquivos abertos!
            """;
        
        JTextArea helpTextArea = new JTextArea(helpText);
        helpTextArea.setEditable(false);
        helpTextArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        helpTextArea.setBackground(Color.WHITE);
        helpTextArea.setOpaque(true);
        
        JScrollPane helpScrollPane = new JScrollPane(helpTextArea);
        helpScrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(
            this, 
            helpScrollPane, 
            "Ajuda - Basic GUI with Threads",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        System.out.println("Diálogo de ajuda exibido");
    }
    
    @Override
    public void showAboutDialog() {
        String aboutText = """
            Basic GUI with Threads
            Versão 1.0
            
            TECNOLOGIAS DEMONSTRADAS:
            ✓ Interface Gráfica (Swing)
            ✓ Multithreading com animações
            ✓ Manipulação de arquivos
            ✓ Diálogos personalizados
            ✓ JLayeredPane para sobreposições
            ✓ ActionListeners e eventos
            
            ARQUITETURA:
            • MainApplication.java (JFrame principal)
            • MenuBarCreator.java (Sistema de menus)
            • ThreadsHandler.java (Animações multithread)
            • Padrão Observer para comunicação
            
            Desenvolvido por: Grupo 03
            """;
        
        JOptionPane.showMessageDialog(
            this,
            aboutText,
            "Sobre - Basic GUI with Threads",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // NOVOS MÉTODOS: Controle adicional das threads (opcional)
    public void pauseAnimation() {
        if (threadsHandler != null) {
            threadsHandler.pauseAnimation();
            statusLabel.setText("Animação pausada");
        }
    }
    
    public void resumeAnimation() {
        if (threadsHandler != null) {
            threadsHandler.resumeAnimation();
            statusLabel.setText("Animação retomada");
        }
    }
    
    public boolean isAnimationRunning() {
        return threadsHandler != null && threadsHandler.isAnimationRunning();
    }
    
    public static void main(String[] args) {
        // Configurar Look and Feel do sistema
        SwingUtilities.invokeLater(MainApplication::new);
    }
}