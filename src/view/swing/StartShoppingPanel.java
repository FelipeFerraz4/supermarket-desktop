package view.swing;

import controllers.PersonController;
import controllers.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartShoppingPanel extends JPanel {
    public StartShoppingPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Cadastro de Produto"));
        add(new JTextField("Nome do Produto"));
        add(new JTextField("Preço"));

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
