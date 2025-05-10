package view.swing.employee;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.MainMenuPanel;
import view.swing.SwingMenu;
import view.swing.employee.people.CreateEmployeePanel;
import view.swing.employee.people.SearchPersonPanel;
import view.swing.employee.people.UpdateEmployeePanel;
import view.swing.employee.people.client.CreateClientAdminPanel;
import view.swing.employee.product.SearchProductsPanel;
import view.swing.employee.product.SelectProductTypePanel;

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

        JButton buttonCreateProduct =
                AuxComponents.createSafeStyledButton(
                        this, "Cadastrar produtos", 300, 50,
                        () -> SwingMenu.changeScreen(new SelectProductTypePanel(personController, productController, person)),
                        "Erro ao abrir tela de cadastro de produtos"
                );

        JButton buttonSearchProduct =
                AuxComponents.createSafeStyledButton(
                        this, "Buscar produtos", 300, 50,
                        () -> SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, person)),
                        "Erro ao abrir tela de busca de produtos"
                );

        JButton buttonCreateClient =
                AuxComponents.createSafeStyledButton(
                        this, "Cadastrar Clientes", 300, 50,
                        () -> SwingMenu.changeScreen(new CreateClientAdminPanel(personController, productController, person)),
                        "Erro ao abrir tela de cadastro de clientes"
                );

        JButton buttonCreateEmployee =
                AuxComponents.createSafeStyledButton(
                        this, "Cadastrar funcionários", 300, 50,
                        () -> SwingMenu.changeScreen(new CreateEmployeePanel(personController, productController, person)),
                        "Erro ao abrir tela de cadastro de funcionários"
                );

        JButton buttonSearchPerson =
                AuxComponents.createSafeStyledButton(
                        this, "Buscar funcionários", 300, 50,
                        () -> SwingMenu.changeScreen(new SearchPersonPanel(personController, productController, person)),
                        "Erro ao abrir tela de busca de funcionários"
                );

        JButton buttonUpdatePerson =
                AuxComponents.createSafeStyledButton(
                        this, "Atualizar Meus dados", 300, 50,
                        () -> SwingMenu.changeScreen(new UpdateEmployeePanel(personController, productController, person, person, 2)),
                        "Erro ao abrir tela de atualização de dados"
                );

        add(AuxComponents.createHorizontalButtonPanel(
                buttonCreateProduct, buttonSearchProduct
        ));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(
                buttonCreateClient, buttonCreateEmployee
        ));
        add(Box.createVerticalStrut(15));
        add(AuxComponents.createHorizontalButtonPanel(
                buttonSearchPerson, buttonUpdatePerson
        ));
        add(Box.createVerticalStrut(80));

        JButton buttonBack =
                AuxComponents.createSafeStyledButton(
                        this, "Voltar", 150, 40,
                        () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController)),
                        "Erro ao voltar para o menu principal"
                );
        add(buttonBack);
    }
}
