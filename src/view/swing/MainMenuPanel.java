package view.swing;

import controllers.PersonController;
import controllers.ProductController;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        JLabel titleLabel = new JLabel("Menu Principal", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(10));

        JButton buttonStartShopping = new JButton("Faça suas compras aqui");
        JButton buttonLogin = new JButton("Faça login e ganhe ponto para trocar por descontos");
        JButton buttonCreateClient = new JButton("Crie sua conta aqui");

        buttonStartShopping.setBackground(Color.GRAY);
        buttonLogin.setBackground(Color.GRAY);
        buttonCreateClient.setBackground(Color.GRAY);

        buttonStartShopping.setForeground(Color.WHITE);
        buttonLogin.setForeground(Color.WHITE);
        buttonCreateClient.setForeground(Color.WHITE);

        Dimension buttonSize = new Dimension(400, 50);
        buttonStartShopping.setPreferredSize(buttonSize);
        buttonStartShopping.setMinimumSize(buttonSize);
        buttonStartShopping.setMaximumSize(buttonSize);
        buttonLogin.setPreferredSize(buttonSize);
        buttonLogin.setMinimumSize(buttonSize);
        buttonLogin.setMaximumSize(buttonSize);
        buttonCreateClient.setPreferredSize(buttonSize);
        buttonCreateClient.setMinimumSize(buttonSize);
        buttonCreateClient.setMaximumSize(buttonSize);

        buttonStartShopping.setAlignmentX(CENTER_ALIGNMENT);
        buttonLogin.setAlignmentX(CENTER_ALIGNMENT);
        buttonCreateClient.setAlignmentX(CENTER_ALIGNMENT);

        buttonStartShopping.addActionListener(_ -> SwingMenu.changeScreen(new StartShoppingPanel(personController, productController)));
        buttonLogin.addActionListener(_ -> SwingMenu.changeScreen(new LoginPanel(personController, productController)));
        buttonCreateClient.addActionListener(_ -> SwingMenu.changeScreen(new CreateClientPanel(personController, productController)));

        add(Box.createVerticalGlue());
        add(buttonStartShopping);
        add(Box.createVerticalStrut(15));
        add(buttonLogin);
        add(Box.createVerticalStrut(15));
        add(buttonCreateClient);
        add(Box.createVerticalGlue());
    }
}
