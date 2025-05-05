package view.swing.product;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.people.ManagePeoplePanel;
import view.swing.product.create.*;

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
        JButton buttonBeverage = AuxComponents.createStyledButton(
                "Bebida", 200, 50,
                () -> SwingMenu.changeScreen(new CreateBeveragePanel(personController, productController, employee))
        );
        JButton buttonProcessedFood = AuxComponents.createStyledButton(
                "Alimento", 200, 50,
                () -> SwingMenu.changeScreen(new CreateProcessedFoodPanel(personController, productController, employee))
        );
        JButton buttonMeat = AuxComponents.createStyledButton(
                "Carne", 200, 50,
                () -> SwingMenu.changeScreen(new CreateMeatPanel(personController, productController, employee))
        );
        JButton buttonFruit = AuxComponents.createStyledButton(
                "Fruta", 200, 50,
                () -> SwingMenu.changeScreen(new CreateFruitPanel(personController, productController, employee))
        );
        JButton buttonHygieneProduct = AuxComponents.createStyledButton(
                "Higiene", 200, 50,
                () -> SwingMenu.changeScreen(new CreateHygieneProductPanel(personController, productController, employee))
        );
        JButton buttonUtensil = AuxComponents.createStyledButton(
                "Utensílio", 200, 50,
                () -> SwingMenu.changeScreen(new CreateUtensilPanel(personController, productController, employee))
        );

        add(AuxComponents.createHorizontalButtonPanel(buttonBeverage, buttonProcessedFood));
        add(AuxComponents.createHorizontalButtonPanel(buttonMeat, buttonFruit));
        add(AuxComponents.createHorizontalButtonPanel(buttonHygieneProduct, buttonUtensil));

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee))
        );

        add(Box.createVerticalStrut(40));
        add(buttonBack);
    }
}
