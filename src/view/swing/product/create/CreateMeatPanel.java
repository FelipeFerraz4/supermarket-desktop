package view.swing.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.MeatDTO;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.product.ManageProductsPanel;

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

        // Campos
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
                List<?> products = productController.getProductsByCategory(Class.forName("model.products.subclasses.Meat"));
                String cod = String.format("ME%04d", products.size() + 1);

                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                LocalDate expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                boolean refrigerated = refrigeratedBox.isSelected();
                String nutritionalInfo = nutritionalInfoField.getText().trim();
                String cutType = cutTypeField.getText().trim();
                String origin = originField.getText().trim();
                boolean isOrganic = organicBox.isSelected();
                String animalType = animalTypeField.getText().trim();
                String storageInstructions = storageInstructionsField.getText().trim();

                MeatDTO meatDTO = new MeatDTO(cod, name, price, amount, expirationDate, weight, refrigerated,
                        nutritionalInfo, cutType, origin, isOrganic, animalType, storageInstructions);
                productController.registerMeat(meatDTO);

                JOptionPane.showMessageDialog(this, "Carne cadastrada com sucesso!");
                SwingMenu.changeScreen(new CreateMeatPanel(personController, productController, employee));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
