package its.SimpleFrame;

public class SimpleFrameDriver {
    public static void main(String[] args) {
        // Criação de dois frames
        SimpleFrame sFrame1 = new SimpleFrame();
        SimpleFrame sFrame2 = new SimpleFrame();

        // Configuração e exibição dos frames
        sFrame1.showIt("SimpleFrame 1");
        sFrame2.showIt("SimpleFrame 2", 300, 300);
    }
}
