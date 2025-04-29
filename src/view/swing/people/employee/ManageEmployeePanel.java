package view.swing.people.employee;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.EmployeePanel;
import view.swing.people.SearchPersonPanel;

import javax.swing.*;
import java.awt.*;

public class ManageEmployeePanel extends JPanel {
    public ManageEmployeePanel(PersonController personController, ProductController productController, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Gerenciar Funcionários");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonCreatePerson =
                AuxComponents.createStyledButton(
                        "Cadastrar funcionário", 400, 50,
                        () -> SwingMenu.changeScreen(new CreateEmployeePanel(personController, productController, person))
                );

        JButton buttonUpdatePerson =
                AuxComponents.createStyledButton(
                        "Atualizar funcionário", 400, 50,
                        () -> SwingMenu.changeScreen(new UpdateEmployeePanel(personController, productController, person))
                );

        JButton buttonSearchPerson =
                AuxComponents.createStyledButton(
                        "Buscar funcionário", 400, 50,
                        () -> SwingMenu.changeScreen(new SearchPersonPanel(personController, productController, person))
                );

        add(AuxComponents.createHorizontalButtonPanel(buttonCreatePerson));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(buttonSearchPerson));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(buttonUpdatePerson));
        add(Box.createVerticalStrut(15));

        JButton buttonBack =
                AuxComponents.createStyledButton(
                        "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, person))
                );
        add(buttonBack);
    }
}
