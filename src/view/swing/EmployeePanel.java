package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.people.client.ManageClientsPanel;
import view.swing.people.ManagePeoplePanel;
import view.swing.product.ManageProductsPanel;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {
    public EmployeePanel(PersonController personController, ProductController productController, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Área Administrativa");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonManageProducts =
                AuxComponents.createStyledButton(
                        "Gerenciar Produtos", 400, 50,
                        () -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, person))
                );

        JButton buttonManageEmployees =
                AuxComponents.createStyledButton(
                        "Gerenciar Pessoas", 400, 50,
                        () -> SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, person))
                );

        add(buttonManageEmployees);
        add(Box.createVerticalStrut(15));
        add(buttonManageProducts);

        add(Box.createVerticalStrut(80));

        JButton buttonBack =
                AuxComponents.createStyledButton(
                        "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController))
                );
        add(buttonBack);
    }
}
