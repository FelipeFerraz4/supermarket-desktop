package view.swing;

import controllers.PersonController;
import controllers.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Lista de Produtos"));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingMenu.changeScreen(new MainMenuPanel(personController, productController));
            }
        });

        add(botaoVoltar);
    }
}
