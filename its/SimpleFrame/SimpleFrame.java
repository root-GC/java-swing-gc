package its.SimpleFrame;

import javax.swing.JFrame;

public class SimpleFrame extends JFrame {

    // Construtor padrão
    public SimpleFrame() {
        setSize(200, 200);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Torna o frame visível
    public void showIt() {
        setVisible(true);
    }

    // Torna o frame visível e define o título
    public void showIt(String title) {
        setTitle(title);
        setVisible(true);
    }

    // Torna o frame visível, define o título e a posição
    public void showIt(String title, int x, int y) {
        setTitle(title);
        setLocation(x, y);
        setVisible(true);
    }

    // Torna o frame invisível
    public void hideIt() {
        setVisible(false);
    }
}
