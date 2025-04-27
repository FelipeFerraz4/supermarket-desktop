package view.swing;

import controllers.PersonController;
import controllers.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClientPanel extends JPanel {
    public CreateClientPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Lista de Produtos"));
        // Aqui futuramente você pode usar uma JTable para mostrar produtos

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
