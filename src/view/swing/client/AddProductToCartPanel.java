package view.swing.client;

import controllers.PersonController;
import controllers.ProductController;
import exceptions.EntityNotFoundException;
import model.people.Person;
import model.products.ProductType;
import model.products.food.Meat;
import model.products.Product;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;

public class AddProductToCartPanel extends JPanel {

    private int currentPage = 0;
    private static final int PRODUCTS_PER_PAGE = 12;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    public AddProductToCartPanel(PersonController personController, ProductController productController, Person client, Map<UUID, Double> cart, ProductType productType) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Adicionar Produto ao Carrinho");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(10));
        add(titleLabel);
        add(Box.createVerticalStrut(10));

        List<Product> allProducts;
        try {
            allProducts = productController.getProductsByCategory(ProductType.getProductClass(productType));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Tipo de produto inválido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage(), "Erro inesperado", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        final Product[] selectedProduct = {null};
        final double[] quantity = {0.0};

        JButton decreaseButton = AuxComponents.createStyledButton("-", 50, 30, () -> {});
        JButton increaseButton = AuxComponents.createStyledButton("+", 50, 30, () -> {});
        JPanel quantityPanel = new JPanel();
        quantityPanel.add(decreaseButton);
        quantityPanel.add(increaseButton);
        add(quantityPanel);

        JButton addButton = AuxComponents.createStyledButton("Adicionar ao Carrinho", 200, 40, () -> {
            try {
                if (selectedProduct[0] == null) {
                    throw new NullPointerException("Nenhum produto selecionado.");
                }
                if (quantity[0] <= 0) {
                    throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
                }

                cart.put(selectedProduct[0].getId(), quantity[0]);

                if (client != null) {
                    personController.updateClientCart(client.getId(), cart);
                }

                JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho.");
            } catch (IllegalArgumentException | NullPointerException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (EntityNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        if (addButton != null) {
            addButton.setEnabled(false);
        }
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
            try {
                productPanel.removeAll();
                int start = currentPage * PRODUCTS_PER_PAGE;
                int end = Math.min(start + PRODUCTS_PER_PAGE, allProducts.size());

                List<Product> pageProducts = allProducts.subList(start, end);
                for (Product product : pageProducts) {
                    String price = decimalFormat.format(product.getPrice());
                    JButton productButton = AuxComponents.createStyledButton(
                            "<html><center>" + product.getName() + "<br/>R$ " + price + "</center></html>",
                            100, 60,
                            () -> {
                                selectedProduct[0] = product;
                                quantity[0] = 1.0;
                                selectedProductLabel.setText("Produto selecionado: " + product.getName());
                                String qtyText = product instanceof Meat
                                        ? decimalFormat.format(quantity[0]) + " kg"
                                        : ((int) quantity[0]) + " un";
                                quantityLabel.setText("Quantidade: " + qtyText);
                                if (addButton != null) {
                                    addButton.setEnabled(true);
                                }
                            }
                    );
                    productPanel.add(productButton);
                }

                productPanel.revalidate();
                productPanel.repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar lista de produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        };

        updateProducts.run();

        if (prevPageButton != null) {
            prevPageButton.addActionListener(_ -> {
                if (currentPage > 0) {
                    currentPage--;
                    updateProducts.run();
                }
            });
        }

        if (nextPageButton != null) {
            nextPageButton.addActionListener(_ -> {
                if ((currentPage + 1) * PRODUCTS_PER_PAGE < allProducts.size()) {
                    currentPage++;
                    updateProducts.run();
                }
            });
        }

        if (decreaseButton != null) {
            decreaseButton.addActionListener(_ -> {
                try {
                    if (selectedProduct[0] == null) throw new NullPointerException();
                    double step = (selectedProduct[0] instanceof Meat) ? 0.1 : 1.0;
                    if (quantity[0] >= step) {
                        quantity[0] -= step;
                        quantity[0] = Math.round(quantity[0] * 10.0) / 10.0;
                        String text = selectedProduct[0] instanceof Meat
                                ? decimalFormat.format(quantity[0]) + " kg"
                                : ((int) quantity[0]) + " un";
                        quantityLabel.setText("Quantidade: " + text);
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Selecione um produto antes de alterar a quantidade.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });
        }

        if (increaseButton != null) {
            increaseButton.addActionListener(_ -> {
                try {
                    if (selectedProduct[0] == null) throw new NullPointerException();
                    double step = (selectedProduct[0] instanceof Meat) ? 0.1 : 1.0;
                    quantity[0] += step;
                    quantity[0] = Math.round(quantity[0] * 10.0) / 10.0;
                    String text = selectedProduct[0] instanceof Meat
                            ? decimalFormat.format(quantity[0]) + " kg"
                            : ((int) quantity[0]) + " un";
                    quantityLabel.setText("Quantidade: " + text);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Selecione um produto antes de alterar a quantidade.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });
        }
    }
}
