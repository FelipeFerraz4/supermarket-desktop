package view.swing.client;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import model.products.ProductType;
import model.products.food.Meat;
import model.products.Product;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class AddProductToCartPanel extends JPanel {

    private int currentPage = 0;
    private static final int PRODUCTS_PER_PAGE = 12;

    public AddProductToCartPanel(PersonController personController, ProductController productController, Person client, Map<UUID, Double> cart, ProductType productType) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Adicionar Produto ao Carrinho");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(10));
        add(titleLabel);
        add(Box.createVerticalStrut(10));

        List<Product> allProducts = productController.getProductsByCategory(ProductType.getProductClass(productType));

        JPanel productPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        productPanel.setMaximumSize(new Dimension(700, 300));
        productPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new Dimension(720, 340));
        add(scrollPane);

        JLabel selectedProductLabel = new JLabel("Produto selecionado: Nenhum");
        selectedProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(selectedProductLabel);

        JLabel quantityLabel = new JLabel("Quantidade: 0");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(quantityLabel);

        JButton decreaseButton = AuxComponents.createStyledButton("-", 50, 30, () -> {});
        JButton increaseButton = AuxComponents.createStyledButton("+", 50, 30, () -> {});

        JPanel quantityPanel = new JPanel();
        quantityPanel.add(decreaseButton);
        quantityPanel.add(increaseButton);
        add(quantityPanel);

        final Product[] selectedProduct = {null};
        final double[] quantity = {0.0};

        JButton addButton = AuxComponents.createStyledButton("Adicionar ao Carrinho", 200, 40,
                () -> {
                    if (selectedProduct[0] == null) {
                        JOptionPane.showMessageDialog(this, "Selecione um produto primeiro.");
                        return;
                    }
                    if (quantity[0] <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantidade inválida.");
                        return;
                    }
                    cart.put(selectedProduct[0].getId(), quantity[0]);
                    if (client != null) {
                        personController.updateClientCart(client.getId(), cart);
                    }
                    JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho.");
                }
        );
        addButton.setEnabled(false);
        add(addButton);

        JButton prevPageButton = AuxComponents.createStyledButton("← Voltar", 150, 40, () -> {});
        JButton nextPageButton = AuxComponents.createStyledButton("Avançar →", 150, 40, () -> {});

        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new ClientPanel(personController, productController, client, cart))
        );

        JPanel navPanel = new JPanel();
        navPanel.add(prevPageButton);
        navPanel.add(backButton);
        navPanel.add(nextPageButton);
        add(Box.createVerticalStrut(10));
        add(navPanel);

        Runnable updateProducts = () -> {
            productPanel.removeAll();

            int start = currentPage * PRODUCTS_PER_PAGE;
            int end = Math.min(start + PRODUCTS_PER_PAGE, allProducts.size());

            List<Product> pageProducts = allProducts.subList(start, end);
            for (Product product : pageProducts) {
                JButton productButton = AuxComponents.createStyledButton(
                        "<html><center>" + product.getName() + "<br/>R$ " + product.getPrice() + "</center></html>",
                        100, 60,
                        () -> {
                            selectedProduct[0] = product;
                            quantity[0] = 1;
                            selectedProductLabel.setText("Produto selecionado: " + product.getName());
                            quantityLabel.setText("Quantidade: " + (product instanceof Meat ? "1.0 kg" : "1 un"));
                            addButton.setEnabled(true);
                        }
                );
                productPanel.add(productButton);
            }

            productPanel.revalidate();
            productPanel.repaint();
        };

        updateProducts.run();

        // Ações dos botões de navegação
        prevPageButton.addActionListener(_ -> {
            if (currentPage > 0) {
                currentPage--;
                updateProducts.run();
            }
        });

        nextPageButton.addActionListener(_ -> {
            if ((currentPage + 1) * PRODUCTS_PER_PAGE < allProducts.size()) {
                currentPage++;
                updateProducts.run();
            }
        });

        // Ações dos botões de quantidade
        decreaseButton.addActionListener(_ -> {
            if (selectedProduct[0] == null) return;
            double step = (selectedProduct[0] instanceof Meat) ? 0.1 : 1.0;
            if (quantity[0] >= step) {
                quantity[0] -= step;
                quantity[0] = Math.round(quantity[0] * 10.0) / 10.0;
                quantityLabel.setText("Quantidade: " + (selectedProduct[0] instanceof Meat ? String.format("%.1f kg", quantity[0]) : (int) quantity[0] + " un"));
            }
        });

        increaseButton.addActionListener(_ -> {
            if (selectedProduct[0] == null) return;
            double step = (selectedProduct[0] instanceof Meat) ? 0.1 : 1.0;
            quantity[0] += step;
            quantity[0] = Math.round(quantity[0] * 10.0) / 10.0;
            quantityLabel.setText("Quantidade: " + (selectedProduct[0] instanceof Meat ? String.format("%.1f kg", quantity[0]) : (int) quantity[0] + " un"));
        });
    }
}
