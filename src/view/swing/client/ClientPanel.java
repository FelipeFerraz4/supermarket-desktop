package view.swing.client;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import model.products.ProductType;
import view.swing.AuxComponents;
import view.swing.MainMenuPanel;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.UUID;

public class ClientPanel extends JPanel {
    public ClientPanel(PersonController personController, ProductController productController, Person client, Map<UUID, Double> cart) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Área do Cliente");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JButton buttonBeverage = AuxComponents.createStyledButton(
                "Bebida", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.DRINK))
        );
        JButton buttonProcessedFood = AuxComponents.createStyledButton(
                "Alimento", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.FOOD))
        );
        JButton buttonMeat = AuxComponents.createStyledButton(
                "Carne", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.MEAT))
        );
        JButton buttonFruit = AuxComponents.createStyledButton(
                "Fruta", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.FRUIT))
        );
        JButton buttonHygieneProduct = AuxComponents.createStyledButton(
                "Higiene", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.HYGIENE))
        );
        JButton buttonUtensil = AuxComponents.createStyledButton(
                "Utensílio", 300, 50,
                () -> SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.UTENSIL))
        );

        add(AuxComponents.createHorizontalButtonPanel(buttonBeverage, buttonProcessedFood));
        add(AuxComponents.createHorizontalButtonPanel(buttonMeat, buttonFruit));
        add(AuxComponents.createHorizontalButtonPanel(buttonHygieneProduct, buttonUtensil));


        JButton buttonViewCart = AuxComponents.createStyledButton(
                "Carrinho e Pagamento", 300, 50,
                () -> SwingMenu.changeScreen(new ViewCartPanel(personController, productController, client, cart))
        );

        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalButtonPanel(
                buttonViewCart
        ));

        add(Box.createVerticalStrut(60));

        JButton buttonBack = AuxComponents.createStyledButton(
                "Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController))
        );
        add(buttonBack);
    }
}
