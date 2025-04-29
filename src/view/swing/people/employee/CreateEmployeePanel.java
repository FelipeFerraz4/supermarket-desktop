package view.swing.people.employee;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;

public class CreateEmployeePanel extends JPanel {
    public CreateEmployeePanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Funcionário");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonBack =
                AuxComponents.createStyledButton(
                        "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new ManageEmployeePanel(personController, productController, employee))
                );
        add(buttonBack);
    }
}
