
// ===== ARQUIVO: MenuBarCreator.java =====
import javax.swing.*;

public class MenuBarCreator {
    private final MenuHandler parentFrame;
    private JMenuBar menuBar;
    
    public MenuBarCreator(MenuHandler parentFrame) {
        this.parentFrame = parentFrame;
        createMenuBar();
    }
    
    public JMenuBar getMenuBar() {
        return menuBar;
    }
    
    private void createMenuBar() {
        menuBar = new JMenuBar();
        
        // Criar os menus
        menuBar.add(createFileMenu());
        menuBar.add(createConfigMenu());
        menuBar.add(createHelpMenu());
    }
    
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Arquivo");
        
        JMenuItem openFile = new JMenuItem("Abrir Arquivo");
        JMenuItem closeFile = new JMenuItem("Fechar Arquivo");
        JMenuItem exit = new JMenuItem("Sair");
        
        // Action Listeners
        openFile.addActionListener(_ -> parentFrame.openFileAction());
        closeFile.addActionListener(_ -> parentFrame.closeFileAction());
        exit.addActionListener(_ -> parentFrame.exitApplication());
        
        // Adicionar ao menu
        fileMenu.add(openFile);
        fileMenu.add(closeFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        return fileMenu;
    }
    
    private JMenu createConfigMenu() {
        JMenu configMenu = new JMenu("Configuração");
        
        JMenuItem patterns = new JMenuItem("Padrões");
        JMenuItem colors = new JMenuItem("Cores");
        JMenuItem speed = new JMenuItem("Velocidade");
        
        // Action Listeners
        patterns.addActionListener(_ -> parentFrame.showPatternsDialog());
        colors.addActionListener(_ -> parentFrame.showColorsDialog());
        speed.addActionListener(_ -> parentFrame.showSpeedDialog());
        
        // Adicionar ao menu
        configMenu.add(patterns);
        configMenu.add(colors);
        configMenu.add(speed);
        
        return configMenu;
    }
    
    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Ajuda");
        
        JMenuItem help = new JMenuItem("Ajuda");
        JMenuItem about = new JMenuItem("Sobre");
        
        // Action Listeners
        help.addActionListener(_ -> parentFrame.showHelpDialog());
        about.addActionListener(_ -> parentFrame.showAboutDialog());
        
        // Adicionar ao menu
        helpMenu.add(help);
        helpMenu.add(about);
        
        return helpMenu;
    }
}