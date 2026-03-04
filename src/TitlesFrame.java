/*
 * Decompiled with CFR 0.152.
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TitlesFrame
extends JFrame {
    public TitlesFrame() {
        this.initUI();
    }

    /**
     * Initializes the user interface with title, window size, and drawing panel.
     */
    private void initUI() {
        this.setTitle("\u041a\u0440\u0438\u0432\u044b\u0435 \u0444\u0438\u0433\u0443\u0440\u044b");
        this.setDefaultCloseOperation(3);
        this.add(new TitlesPanel(94));
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
    }

    /**
     * Main entry point for the application. Launches the window on the EDT.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                TitlesFrame ps = new TitlesFrame();
                ps.setVisible(true);
            }
        });
    }
}

