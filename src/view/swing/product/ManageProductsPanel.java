package view.swing.product;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.EmployeePanel;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;

public class ManageProductsPanel extends JPanel {
    public ManageProductsPanel(PersonController personController, ProductController productController, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Gerenciar Produtos");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonCreateProduct =
                AuxComponents.createStyledButton(
                        "Cadastrar produto", 400, 50,
                        () -> SwingMenu.changeScreen(new CreateProductPanel(personController, productController, person))
                );
        JButton buttonUpdateProduct =
                AuxComponents.createStyledButton(
                        "Atualizar produto", 400, 50,
                        () -> SwingMenu.changeScreen(new UpdateProductPanel(personController, productController, person))
                );
        JButton buttonSearchProduct =
                AuxComponents.createStyledButton(
                        "Buscar produto por nome", 400, 50,
                        () -> SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, person))
                );

        add(AuxComponents.createHorizontalButtonPanel(buttonCreateProduct));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(buttonSearchProduct));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(buttonUpdateProduct));
        add(Box.createVerticalStrut(15));

        JButton buttonBack =
                AuxComponents.createStyledButton(
                        "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, person))
                );
        add(buttonBack);
    }
}
