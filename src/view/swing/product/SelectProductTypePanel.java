package view.swing.product;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.people.ManagePeoplePanel;

import javax.swing.*;
import java.awt.*;

public class SelectProductTypePanel extends JPanel {
    public SelectProductTypePanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Selecione o tipo de produto");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        // Criando os botões para os tipos de produto
        JButton buttonBebida = AuxComponents.createStyledButton(
                "Bebida", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );
        JButton buttonAlimento = AuxComponents.createStyledButton(
                "Alimento", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );
        JButton buttonCarne = AuxComponents.createStyledButton(
                "Carne", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );
        JButton buttonFruta = AuxComponents.createStyledButton(
                "Fruta", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );
        JButton buttonHigiene = AuxComponents.createStyledButton(
                "Higiene", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );
        JButton buttonUtensilio = AuxComponents.createStyledButton(
                "Utensílio", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, employee))
        );

        add(AuxComponents.createHorizontalButtonPanel(buttonBebida, buttonAlimento));
        add(AuxComponents.createHorizontalButtonPanel(buttonCarne, buttonFruta));
        add(AuxComponents.createHorizontalButtonPanel(buttonHigiene, buttonUtensilio));

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee))
        );

        add(Box.createVerticalStrut(40));
        add(buttonBack);
    }
}
