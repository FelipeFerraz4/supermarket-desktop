package view.swing;

import controllers.PersonController;
import controllers.ProductController;

import javax.swing.*;
import java.awt.*;

public class SwingMenu {
    private static JFrame frame;

    public static void display(PersonController personController, ProductController productController) {
        try {
            frame = new JFrame("Supermercado - Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null);

            changeScreen(new MainMenuPanel(personController, productController));

            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao inicializar a interface: " + e.getMessage(),
                    "Erro de Inicialização", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void changeScreen(JPanel novoPainel) {
        try {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(novoPainel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao trocar de tela: " + e.getMessage(),
                    "Erro ao Trocar Tela", JOptionPane.ERROR_MESSAGE);
        }
    }
}
