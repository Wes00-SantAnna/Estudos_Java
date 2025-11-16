import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class mainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField threadsField;
    private final JTextField numTasksField;
    private final JPanel tasksPanel;

    public mainGUI() {
        setTitle("Task Executor GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        topPanel.add(new JLabel("Número de threads:"));
        threadsField = new JTextField();
        topPanel.add(threadsField);

        topPanel.add(new JLabel("Número de tarefas:"));
        numTasksField = new JTextField();
        topPanel.add(numTasksField);

        add(topPanel, BorderLayout.NORTH);

        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(tasksPanel), BorderLayout.CENTER);

        JButton gerarBotoes = new JButton("Gerar campos");
        gerarBotoes.addActionListener(ev -> gerarCamposDeTarefa());
        add(gerarBotoes, BorderLayout.WEST);

        JButton executarBtn = new JButton("Executar Tarefas");
        executarBtn.addActionListener(ev -> executarTarefas());
        add(executarBtn, BorderLayout.SOUTH);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void gerarCamposDeTarefa() {
        tasksPanel.removeAll();
        int numTasks = Integer.parseInt(numTasksField.getText());

        for (int i = 1; i <= numTasks; i++) {
            tasksPanel.add(new JLabel("Valor da tarefa " + i + ":"));
            tasksPanel.add(new JTextField());
        }

        tasksPanel.revalidate();
        tasksPanel.repaint();
    }

    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
    private void executarTarefas() {
        int numThreads = Integer.parseInt(threadsField.getText());
        TaskExecutor executor = new TaskExecutor(numThreads);

        Component[] components = tasksPanel.getComponents();
        List<Integer> values = new ArrayList<>();

        for (Component c : components) {
            if (c instanceof JTextField tf) {
                if (!tf.getText().trim().isEmpty()) {
                    values.add(Integer.parseInt(tf.getText().trim()));
                }
            }
        }

        for (int v : values) {
            executor.submitTask(new Task(v));
        }

        new Thread(() -> {
            executor.start();
            executor.waitForCompletion();
            JOptionPane.showMessageDialog(this, "Todas as tarefas concluídas!");
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(mainGUI::new);
    }
}
