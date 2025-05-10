package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.UtensilDTO;
import exceptions.DuplicateEntityException;
import model.people.Person;
import model.products.Utensil;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateUtensilPanel extends JPanel {
    public CreateUtensilPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Utensílio");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(30));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField materialField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField sizeField = new JTextField();
        JCheckBox reusableBox = new JCheckBox("É reutilizável?");

        add(AuxComponents.createHorizontalFields(
                "Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Quantidade:", 14, amountField, 300, 25,
                "Material:", 14, materialField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Categoria:", 14, categoryField, 300, 25,
                "Tamanho:", 14, sizeField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalCheckBoxes(reusableBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                // Validações de dados antes de prosseguir
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("O nome do utensílio não pode ser vazio.");
                }

                double price;
                try {
                    price = Double.parseDouble(priceField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("O preço deve ser um valor numérico.");
                }

                int amount;
                try {
                    amount = Integer.parseInt(amountField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("A quantidade deve ser um número inteiro.");
                }

                String material = materialField.getText().trim();
                String category = categoryField.getText().trim();
                boolean isReusable = reusableBox.isSelected();
                String size = sizeField.getText().trim();

                // Verifica se algum campo obrigatório está vazio
                if (material.isEmpty() || category.isEmpty() || size.isEmpty()) {
                    throw new IllegalArgumentException("Material, categoria e tamanho não podem ser vazios.");
                }

                // Gera o código do utensílio
                List<?> products = productController.getProductsByCategory(Utensil.class);
                String cod = String.format("UT%04d", products.size() + 1);

                // Cria o DTO do utensílio
                UtensilDTO utensilDTO = new UtensilDTO(cod, name, price, amount, material, category, isReusable, size);

                // Tenta registrar o utensílio
                try {
                    productController.registerUtensil(utensilDTO);
                    JOptionPane.showMessageDialog(this, "Utensílio cadastrado com sucesso!");
                    SwingMenu.changeScreen(new CreateUtensilPanel(personController, productController, employee));
                } catch (DuplicateEntityException e) {
                    JOptionPane.showMessageDialog(this, "Erro: Já existe esse utensílio");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro de validação: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new SelectProductTypePanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
