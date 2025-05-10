package view.swing.employee.product;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.employee.EmployeePanel;
import view.swing.SwingMenu;
import view.swing.employee.product.create.*;

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

        JButton buttonBeverage = AuxComponents.createSafeStyledButton(
                this, "Bebida", 200, 50,
                () -> SwingMenu.changeScreen(new CreateBeveragePanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de bebida"
        );
        JButton buttonProcessedFood = AuxComponents.createSafeStyledButton(
                this, "Alimento", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProcessedFoodPanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de alimento"
        );
        JButton buttonMeat = AuxComponents.createSafeStyledButton(
                this, "Carne", 200, 50,
                () -> SwingMenu.changeScreen(new CreateMeatPanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de carne"
        );
        JButton buttonFruit = AuxComponents.createSafeStyledButton(
                this, "Fruta", 200, 50,
                () -> SwingMenu.changeScreen(new CreateFruitPanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de fruta"
        );
        JButton buttonHygieneProduct = AuxComponents.createSafeStyledButton(
                this, "Higiene", 200, 50,
                () -> SwingMenu.changeScreen(new CreateHygieneProductPanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de produto de higiene"
        );
        JButton buttonUtensil = AuxComponents.createSafeStyledButton(
                this, "Utensílio", 200, 50,
                () -> SwingMenu.changeScreen(new CreateUtensilPanel(personController, productController, employee)),
                "Erro ao abrir tela de cadastro de utensílio"
        );

        add(AuxComponents.createHorizontalButtonPanel(buttonBeverage, buttonProcessedFood));
        add(AuxComponents.createHorizontalButtonPanel(buttonMeat, buttonFruit));
        add(AuxComponents.createHorizontalButtonPanel(buttonHygieneProduct, buttonUtensil));

        JButton buttonBack = AuxComponents.createSafeStyledButton(
                this, "Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, employee)),
                "Erro ao voltar para o painel do funcionário"
        );

        add(Box.createVerticalStrut(40));
        add(buttonBack);
    }
}
