// ===== ARQUIVO: ThreadsHandler.java =====
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadsHandler extends JPanel {
    
    // Configurações da animação
    private boolean animationRunning = false;
    private int animationSpeed = 50; // milliseconds delay
    private String animationPattern = "CIRCLES";
    private Color primaryColor = Color.BLUE;
    private Color secondaryColor = Color.CYAN;
    
    // Thread da animação
    private Thread animationThread;
    private final Object animationLock = new Object();
    
    // Elementos gráficos para animação
    private List<AnimationElement> elements;
    private Random random;
    private Timer repaintTimer;
    
    public ThreadsHandler() {
        initializeComponents();
        setupPanel();
        createAnimationElements();
    }
    
    private void initializeComponents() {
        elements = new ArrayList<>();
        random = new Random();
        
        // Timer para repaint (60 FPS)
        repaintTimer = new Timer(16, _ -> repaint());
    }
    
    private void setupPanel() {
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Para animações suaves
        this.setPreferredSize(new Dimension(800, 600));
    }
    
    private void createAnimationElements() {
        elements.clear();
        
        // Criar elementos baseados no padrão selecionado
        switch (animationPattern) {
            case "CIRCLES" -> createCircleElements();
            case "SQUARES" -> createSquareElements();
            case "LINES" -> createLineElements();
            case "PARTICLES" -> createParticleElements();
            default -> createCircleElements();
        }
    }
    
    private void createCircleElements() {
        for (int i = 0; i < 15; i++) {
            elements.add(new CircleElement(
                random.nextInt(getWidth() > 0 ? getWidth() : 800),
                random.nextInt(getHeight() > 0 ? getHeight() : 600),
                random.nextInt(50) + 20, // raio
                random.nextDouble() * 4 - 2, // velocidade X
                random.nextDouble() * 4 - 2, // velocidade Y
                getRandomColor()
            ));
        }
    }
    
    private void createSquareElements() {
        for (int i = 0; i < 12; i++) {
            elements.add(new SquareElement(
                random.nextInt(getWidth() > 0 ? getWidth() : 800),
                random.nextInt(getHeight() > 0 ? getHeight() : 600),
                random.nextInt(40) + 15, // tamanho
                random.nextDouble() * 3 - 1.5, // velocidade X
                random.nextDouble() * 3 - 1.5, // velocidade Y
                getRandomColor()
            ));
        }
    }
    
    private void createLineElements() {
        for (int i = 0; i < 20; i++) {
            elements.add(new LineElement(
                random.nextInt(getWidth() > 0 ? getWidth() : 800),
                random.nextInt(getHeight() > 0 ? getHeight() : 600),
                random.nextInt(100) + 50, // comprimento
                random.nextDouble() * Math.PI * 2, // ângulo
                random.nextDouble() * 2 - 1, // velocidade angular
                getRandomColor()
            ));
        }
    }
    
    private void createParticleElements() {
        for (int i = 0; i < 50; i++) {
            elements.add(new ParticleElement(
                random.nextInt(getWidth() > 0 ? getWidth() : 800),
                random.nextInt(getHeight() > 0 ? getHeight() : 600),
                random.nextInt(8) + 3, // tamanho
                random.nextDouble() * 6 - 3, // velocidade X
                random.nextDouble() * 6 - 3, // velocidade Y
                getRandomColor()
            ));
        }
    }
    
    private Color getRandomColor() {
        // Misturar cores primária e secundária com variações
        int r = (primaryColor.getRed() + secondaryColor.getRed()) / 2 + random.nextInt(100) - 50;
        int g = (primaryColor.getGreen() + secondaryColor.getGreen()) / 2 + random.nextInt(100) - 50;
        int b = (primaryColor.getBlue() + secondaryColor.getBlue()) / 2 + random.nextInt(100) - 50;
        
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));
        
        return new Color(r, g, b, 150); // Semi-transparente
    }
    
    // ===== CONTROLE DA ANIMAÇÃO =====
    
    public void startAnimation() {
        synchronized (animationLock) {
            if (!animationRunning) {
                animationRunning = true;
                repaintTimer.start();
                
                animationThread = new Thread(this::animationLoop, "AnimationThread");
                animationThread.setDaemon(true); // Thread daemon
                animationThread.start();
                
                System.out.println("Animação iniciada - Thread: " + animationThread.getName());
            }
        }
    }
    
    public void stopAnimation() {
        synchronized (animationLock) {
            if (animationRunning) {
                animationRunning = false;
                repaintTimer.stop();
                
                if (animationThread != null) {
                    animationThread.interrupt();
                }
                
                System.out.println("Animação parada");
            }
        }
    }
    
    public void pauseAnimation() {
        repaintTimer.stop();
        System.out.println("Animação pausada");
    }
    
    public void resumeAnimation() {
        if (animationRunning) {
            repaintTimer.start();
            System.out.println("Animação retomada");
        }
    }
    
    // Loop principal da thread de animação
    private void animationLoop() {
        long lastUpdateTime = System.currentTimeMillis();
        final Object lock = new Object();
        
        while (animationRunning && !Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            
            // Atualizar apenas se passou tempo suficiente baseado na velocidade
            if (currentTime - lastUpdateTime >= animationSpeed) {
                // Atualizar posições dos elementos
                updateElements();
                lastUpdateTime = currentTime;
            }
            
            // Usar wait() com timeout curto para controle de timing
            synchronized (lock) {
                try {
                    lock.wait(1); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    private void updateElements() {
        for (AnimationElement element : elements) {
            element.update(getWidth(), getHeight());
        }
    }
    
    // ===== CONFIGURAÇÕES =====
    
    public void setAnimationSpeed(int speed) {
        this.animationSpeed = Math.max(10, Math.min(200, speed));
        System.out.println("Velocidade da animação: " + this.animationSpeed + "ms");
    }
    
    public void setAnimationPattern(String pattern) {
        this.animationPattern = pattern;
        createAnimationElements();
        System.out.println("Padrão da animação: " + pattern);
    }
    
    public void setColors(Color primary, Color secondary) {
        this.primaryColor = primary;
        this.secondaryColor = secondary;
        createAnimationElements();
        System.out.println("Cores atualizadas: " + primary + ", " + secondary);
    }
    
    public boolean isAnimationRunning() {
        return animationRunning;
    }
    
    // ===== RENDERIZAÇÃO =====
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Ativar anti-aliasing para gráficos suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Desenhar todos os elementos
        for (AnimationElement element : elements) {
            element.draw(g2d);
        }
        
        // Desenhar informações da thread no canto superior direito
        drawThreadInfo(g2d);
        
        g2d.dispose();
    }
    
    // Desenhar informações da thread no canto superior direito
    private void drawThreadInfo(Graphics2D g2d) {
        // Configurações do texto
        Font font = new Font("Arial", Font.BOLD, 11);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics(font);
        
        // Preparar as informações a serem exibidas
        String[] info = {
            "🧵 THREAD INFO",
            "Status: " + (animationRunning ? "ATIVA" : "PARADA"),
            "Thread: " + (animationThread != null && animationThread.isAlive() ? 
                         animationThread.getName() : "Nenhuma"),
            "Estado: " + (animationThread != null ? animationThread.getState().toString() : "N/A"),
            "Elementos: " + elements.size(),
            "Padrão: " + animationPattern,
            "Velocidade: " + animationSpeed + "ms",
            "FPS: ~" + Math.round(1000.0 / Math.max(1, animationSpeed))
        };
        
        // Calcular posições no canto superior direito
        int rightMargin = 15;
        int topMargin = 20;
        int lineHeight = fm.getHeight();
        int maxWidth = 0;
        
        // Encontrar a largura máxima do texto
        for (String line : info) {
            maxWidth = Math.max(maxWidth, fm.stringWidth(line));
        }
        
        // Desenhar fundo semi-transparente
        int bgX = getWidth() - maxWidth - rightMargin - 10;
        int bgY = topMargin - fm.getAscent() - 5;
        int bgWidth = maxWidth + 15;
        int bgHeight = info.length * lineHeight + 10;
        
        g2d.setColor(new Color(0, 0, 0, 150)); // Fundo preto semi-transparente
        g2d.fillRoundRect(bgX, bgY, bgWidth, bgHeight, 8, 8);
        
        // Desenhar borda
        g2d.setColor(animationRunning ? new Color(0, 255, 0, 180) : new Color(255, 0, 0, 180));
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRoundRect(bgX, bgY, bgWidth, bgHeight, 8, 8);
        
        // Desenhar o texto
        for (int i = 0; i < info.length; i++) {
            int x = getWidth() - fm.stringWidth(info[i]) - rightMargin;
            int y = topMargin + (i * lineHeight);
            
            // Cor do texto baseada no tipo de informação
            if (i == 0) { // Título
                g2d.setColor(new Color(255, 255, 0, 255)); // Amarelo
            } else if (info[i].contains("ATIVA")) {
                g2d.setColor(new Color(0, 255, 0, 255)); // Verde
            } else if (info[i].contains("PARADA")) {
                g2d.setColor(new Color(255, 100, 100, 255)); // Vermelho claro
            } else {
                g2d.setColor(new Color(255, 255, 255, 230)); // Branco
            }
            
            g2d.drawString(info[i], x, y);
        }
    }
}

// ===== CLASSES DOS ELEMENTOS DE ANIMAÇÃO =====

abstract class AnimationElement {
    protected double x, y;
    protected Color color;
    
    public AnimationElement(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public abstract void update(int width, int height);
    public abstract void draw(Graphics2D g2d);
    
    protected void bounceOffWalls(int width, int height, double size) {
        if (x < 0 || x + size > width) {
            x = Math.max(0, Math.min(width - size, x));
        }
        if (y < 0 || y + size > height) {
            y = Math.max(0, Math.min(height - size, y));
        }
    }
}

class CircleElement extends AnimationElement {
    private double vx, vy;
    private final double radius;
    
    public CircleElement(double x, double y, double radius, double vx, double vy, Color color) {
        super(x, y, color);
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;
    }
    
    @Override
    public void update(int width, int height) {
        x += vx;
        y += vy;
        
        // Rebater nas bordas
        if (x <= 0 || x >= width - radius) vx = -vx;
        if (y <= 0 || y >= height - radius) vy = -vy;
        
        bounceOffWalls(width, height, radius);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval((int)(x - radius/2), (int)(y - radius/2), (int)radius, (int)radius);
    }
}

class SquareElement extends AnimationElement {
    private double vx, vy;
    private final double size;
    private double rotation = 0;
    
    public SquareElement(double x, double y, double size, double vx, double vy, Color color) {
        super(x, y, color);
        this.size = size;
        this.vx = vx;
        this.vy = vy;
    }
    
    @Override
    public void update(int width, int height) {
        x += vx;
        y += vy;
        rotation += 0.05;
        
        if (x <= 0 || x >= width - size) vx = -vx;
        if (y <= 0 || y >= height - size) vy = -vy;
        
        bounceOffWalls(width, height, size);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.translate(x, y);
        g2d.rotate(rotation);
        g2d.fillRect(-(int)size/2, -(int)size/2, (int)size, (int)size);
        g2d.rotate(-rotation);
        g2d.translate(-x, -y);
    }
}

class LineElement extends AnimationElement {
    private final double length, angularVelocity;
    private double angle;
    
    public LineElement(double x, double y, double length, double angle, double angularVelocity, Color color) {
        super(x, y, color);
        this.length = length;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
    }
    
    @Override
    public void update(int width, int height) {
        angle += angularVelocity;
        
        // Movimentar lentamente
        x += Math.cos(angle) * 0.5;
        y += Math.sin(angle) * 0.5;
        
        // Wrap around screen
        if (x < 0) x = width;
        if (x > width) x = 0;
        if (y < 0) y = height;
        if (y > height) y = 0;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));
        
        double endX = x + Math.cos(angle) * length;
        double endY = y + Math.sin(angle) * length;
        
        g2d.drawLine((int)x, (int)y, (int)endX, (int)endY);
    }
}

class ParticleElement extends AnimationElement {
    private double vx, vy, size;
    private double life = 1.0;
    
    public ParticleElement(double x, double y, double size, double vx, double vy, Color color) {
        super(x, y, color);
        this.size = size;
        this.vx = vx;
        this.vy = vy;
    }
    
    @Override
    public void update(int width, int height) {
        x += vx;
        y += vy;
        
        // Diminuir velocidade gradualmente
        vx *= 0.99;
        vy *= 0.99;
        
        // Wrap around
        if (x < 0) x = width;
        if (x > width) x = 0;
        if (y < 0) y = height;
        if (y > height) y = 0;
        
        // Efeito de "respiração"
        life += 0.1;
        size = size + Math.sin(life) * 2;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval((int)(x - size/2), (int)(y - size/2), (int)size, (int)size);
    }
}