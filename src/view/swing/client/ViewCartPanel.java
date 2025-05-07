package view.swing.client;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import model.products.Product;
import model.products.food.Meat;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.UUID;

public class ViewCartPanel extends JPanel {

    public ViewCartPanel(PersonController personController, ProductController productController, Person client, Map<UUID, Double> cart) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Carrinho de Compras");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(20));

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        cartPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane);

        Runnable refresh = () -> SwingMenu.changeScreen(new ViewCartPanel(personController, productController, client, cart));

        if (cart.isEmpty()) {
            JLabel emptyLabel = new JLabel("Carrinho vazio.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartPanel.add(emptyLabel);
        } else {
            double total = 0.0;

            for (Map.Entry<UUID, Double> entry : cart.entrySet()) {
                UUID id = entry.getKey();
                double quantity = entry.getValue();

                Product product = productController.searchById(id);
                if (product == null) {
                    System.out.println("Produto com ID " + id + " não encontrado.");
                    continue;
                }

                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
                itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
                itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                String quantityText = (product instanceof Meat)
                        ? String.format("%.1f kg", quantity)
                        : String.format("%d un", (int) quantity);
                double subtotal = product.getPrice() * quantity;

                JLabel label = new JLabel(String.format("%s - %s - R$ %.2f", product.getName(), quantityText, subtotal));
                label.setFont(new Font("Arial", Font.PLAIN, 16));

                label.setPreferredSize(new Dimension(300, 30));

                JButton decreaseButton = AuxComponents.createStyledButton("-", 100, 30, () -> {
                    double step = (product instanceof Meat) ? 0.1 : 1.0;
                    double newQuantity = quantity - step;
                    if (newQuantity <= 0.0) {
                        cart.remove(id);
                        if (client != null) {
                            personController.updateClientCart(client.getId(), cart);
                        }
                    } else {
                        cart.put(id, Math.round(newQuantity * 10.0) / 10.0);
                        if (client != null) {
                            personController.updateClientCart(client.getId(), cart);
                        }
                    }
                    refresh.run();
                });

                JButton increaseButton = AuxComponents.createStyledButton("+", 100, 30, () -> {
                    double step = (product instanceof Meat) ? 0.1 : 1.0;
                    double newQuantity = quantity + step;
                    cart.put(id, Math.round(newQuantity * 10.0) / 10.0);
                    if (client != null) {
                        personController.updateClientCart(client.getId(), cart);
                    }
                    refresh.run();
                });

                itemPanel.add(label);
                itemPanel.add(Box.createHorizontalStrut(10));
                itemPanel.add(decreaseButton);
                itemPanel.add(Box.createHorizontalStrut(5));
                itemPanel.add(increaseButton);

                cartPanel.add(itemPanel);

                total += subtotal;
            }

            JLabel totalLabel = new JLabel(String.format("Total: R$ %.2f", total));
            totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
            totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalStrut(10));
            add(totalLabel);
        }

        JButton payButton = AuxComponents.createStyledButton("Finalizar Pagamento", 200, 40, () -> {
            JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
            cart.clear(); // esvazia o carrinho após pagamento
            if (client != null) {
                personController.updateClientCart(client.getId(), cart);
            }
            refresh.run(); // atualiza tela
        });

        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new ClientPanel(personController, productController, client, cart))
        );
        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(
                backButton, payButton
        ));
        add(Box.createVerticalStrut(5));
    }
}
