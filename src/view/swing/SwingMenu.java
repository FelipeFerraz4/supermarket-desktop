package view.swing;

import javax.swing.*;
import java.awt.*;

public class SwingMenu {
    private static JFrame frame;

    public static void display() {
        frame = new JFrame("Supermercado - Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        changeScreen(new MainMenuPanel());

        frame.setVisible(true);
    }

    public static void changeScreen(JPanel novoPainel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(novoPainel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
