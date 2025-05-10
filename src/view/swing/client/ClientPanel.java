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
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.DRINK));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Bebida", e);
                    }
                }
        );

        JButton buttonProcessedFood = AuxComponents.createStyledButton(
                "Alimento", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.FOOD));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Alimento", e);
                    }
                }
        );

        JButton buttonMeat = AuxComponents.createStyledButton(
                "Carne", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.MEAT));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Carne", e);
                    }
                }
        );

        JButton buttonFruit = AuxComponents.createStyledButton(
                "Fruta", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.FRUIT));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Fruta", e);
                    }
                }
        );

        JButton buttonHygieneProduct = AuxComponents.createStyledButton(
                "Higiene", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.HYGIENE));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Higiene", e);
                    }
                }
        );

        JButton buttonUtensil = AuxComponents.createStyledButton(
                "Utensílio", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new AddProductToCartPanel(personController, productController, client, cart, ProductType.UTENSIL));
                    } catch (Exception e) {
                        showError("Erro ao abrir categoria Utensílio", e);
                    }
                }
        );

        add(AuxComponents.createHorizontalButtonPanel(buttonBeverage, buttonProcessedFood));
        add(AuxComponents.createHorizontalButtonPanel(buttonMeat, buttonFruit));
        add(AuxComponents.createHorizontalButtonPanel(buttonHygieneProduct, buttonUtensil));

        JButton buttonViewCart = AuxComponents.createStyledButton(
                "Carrinho e Pagamento", 300, 50,
                () -> {
                    try {
                        SwingMenu.changeScreen(new ViewCartPanel(personController, productController, client, cart));
                    } catch (Exception e) {
                        showError("Erro ao abrir o carrinho", e);
                    }
                }
        );

        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalButtonPanel(buttonViewCart));
        add(Box.createVerticalStrut(60));

        JButton buttonBack = AuxComponents.createStyledButton(
                "Voltar", 150, 40,
                () -> {
                    try {
                        SwingMenu.changeScreen(new MainMenuPanel(personController, productController));
                    } catch (Exception e) {
                        showError("Erro ao voltar para o menu principal", e);
                    }
                }
        );

        add(buttonBack);
    }

    private void showError(String message, Exception e) {
        JOptionPane.showMessageDialog(this, message + ":\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
