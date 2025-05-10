package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.MeatDTO;
import exceptions.DuplicateEntityException;
import model.people.Person;
import model.products.food.Meat;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CreateMeatPanel extends JPanel {
    public CreateMeatPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Carnes");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(30));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField expirationDateField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField nutritionalInfoField = new JTextField();
        JTextField cutTypeField = new JTextField();
        JTextField originField = new JTextField();
        JTextField animalTypeField = new JTextField();
        JTextField storageInstructionsField = new JTextField();

        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado?");
        JCheckBox organicBox = new JCheckBox("Orgânico?");

        add(AuxComponents.createHorizontalFields(
                "Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Quantidade:", 14, amountField, 300, 25,
                "Data de validade (AAAA-MM-DD):", 14, expirationDateField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Peso (Kg):", 14, weightField, 300, 25,
                "Tipo de Corte:", 14, cutTypeField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Origem:", 14, originField, 300, 25,
                "Tipo de Animal:", 14, animalTypeField, 300, 25
        ));
        add(AuxComponents.createLabeledField(
                "Informações Nutricionais:", 14, nutritionalInfoField, 620, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField(
                "Instruções de Armazenamento:", 14, storageInstructionsField, 620, 25
        ));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, organicBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                if (nameField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() ||
                        amountField.getText().trim().isEmpty() || expirationDateField.getText().trim().isEmpty() ||
                        weightField.getText().trim().isEmpty() || cutTypeField.getText().trim().isEmpty() ||
                        originField.getText().trim().isEmpty() || animalTypeField.getText().trim().isEmpty() ||
                        nutritionalInfoField.getText().trim().isEmpty() || storageInstructionsField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos são obrigatórios.");
                }

                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                LocalDate expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());

                if (price <= 0 || amount <= 0 || weight <= 0) {
                    throw new IllegalArgumentException("Preço, quantidade e peso devem ser maiores que zero.");
                }

                List<?> products = productController.getProductsByCategory(Meat.class);
                String cod = String.format("ME%04d", products.size() + 1);

                String name = nameField.getText().trim();
                String cutType = cutTypeField.getText().trim();
                String origin = originField.getText().trim();
                boolean refrigerated = refrigeratedBox.isSelected();
                String nutritionalInfo = nutritionalInfoField.getText().trim();
                boolean isOrganic = organicBox.isSelected();
                String animalType = animalTypeField.getText().trim();
                String storageInstructions = storageInstructionsField.getText().trim();

                MeatDTO meatDTO = new MeatDTO(cod, name, price, amount, expirationDate, weight, refrigerated,
                        nutritionalInfo, cutType, origin, isOrganic, animalType, storageInstructions);

                productController.registerMeat(meatDTO);

                JOptionPane.showMessageDialog(this, "Carne cadastrada com sucesso!");
                SwingMenu.changeScreen(new CreateMeatPanel(personController, productController, employee));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro de validação: " + e.getMessage());
            } catch (DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
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
