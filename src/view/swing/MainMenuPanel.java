package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import view.swing.client.ClientPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        JLabel titleLabel = new JLabel("Menu Principal", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(10));

        JButton buttonStartShopping =
                AuxComponents.createStyledButton(
                        "Faça suas compras aqui", 400, 50,
                        () -> SwingMenu.changeScreen(new ClientPanel(personController, productController, null, new HashMap<>()))
                );

        JButton buttonLogin =
                AuxComponents.createStyledButton(
                        "Faça login e ganhe ponto para trocar por descontos", 400, 50,
                        () -> SwingMenu.changeScreen(new LoginPanel(personController, productController))
                );

        JButton buttonCreateClient =
                AuxComponents.createStyledButton(
                        "Crie sua conta aqui", 400, 50,
                        () -> SwingMenu.changeScreen(new CreateClientPanel(personController, productController))
                );

        add(Box.createVerticalGlue());
        add(buttonStartShopping);
        add(Box.createVerticalStrut(15));
        add(buttonLogin);
        add(Box.createVerticalStrut(15));
        add(buttonCreateClient);
        add(Box.createVerticalGlue());
    }
}
