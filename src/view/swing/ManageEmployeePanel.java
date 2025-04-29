package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;

import javax.swing.*;
import java.awt.*;

public class ManageEmployeePanel extends JPanel {
    public ManageEmployeePanel(PersonController personController, ProductController productController, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Gerenciar Pessoas");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonBack =
                AuxComponents.createStyledButton(
                        "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, person))
                );
        add(buttonBack);
    }
}
