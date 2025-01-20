import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//(Normalmente eh armazenado em pacotes)
// MODELO: Gerencia os dados e o estado da barra de rolagem.
class ScrollBarModel {
    private int min; // Valor mínimo
    private int max; // Valor máximo
    private int current; // Valor atual

    public ScrollBarModel(int min, int max, int current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= min && current <= max) {
            this.current = current; // Atualiza o valor atual dentro dos limites
        }
    }
}

// VISÃO: Desenha a barra de rolagem na tela.
class ScrollBarView extends JPanel {
    private ScrollBarModel model;

    public ScrollBarView(ScrollBarModel model) {
        this.model = model;
        this.setPreferredSize(new Dimension(200, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Desenha a "track" (trilho da barra de rolagem)
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, height / 2 - 5, width, 10);

        // Calcula a posição do "thumb" (a parte que desliza)
        int thumbWidth = 20;
        int range = model.getMax() - model.getMin();
        int position = (int) ((float) (model.getCurrent() - model.getMin()) / range * (width - thumbWidth));

        // Desenha o "thumb"
        g.setColor(Color.BLUE);
        g.fillRect(position, height / 2 - 10, thumbWidth, 20);
    }
}

// CONTROLADOR: Gerencia os eventos do usuário (cliques e arrastes).
class ScrollBarController extends MouseAdapter {
    private ScrollBarModel model;
    private ScrollBarView view;

    public ScrollBarController(ScrollBarModel model, ScrollBarView view) {
        this.model = model;
        this.view = view;
        this.view.addMouseListener(this);
        this.view.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(); // Obtém a posição do clique
        updateModel(x);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX(); // Obtém a posição do arrasto
        updateModel(x);
    }

    private void updateModel(int x) {
        int width = view.getWidth();
        int thumbWidth = 20;
        int range = model.getMax() - model.getMin();

        // Calcula o novo valor com base na posição do clique/arrasto
        int newValue = model.getMin() + (x * range) / (width - thumbWidth);

        // Atualiza o modelo
        model.setCurrent(newValue);

        // Re-renderiza a visão
        view.repaint();
    }
}

// CLASSE PRINCIPAL: Junta tudo.
public class ScrollBarMVCExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria o modelo
            ScrollBarModel model = new ScrollBarModel(0, 100, 50);

            // Cria a visão
            ScrollBarView view = new ScrollBarView(model);

            // Cria o controlador
            new ScrollBarController(model, view);

            // Configura a janela
            JFrame frame = new JFrame("Barra de Rolagem MVC");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(view, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
