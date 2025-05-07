package view.swing.employee.product.update;

import controllers.PersonController;
import controllers.ProductController;
import dtos.BeverageDTO;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SearchProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class UpdateBeveragePanel extends JPanel {
    public UpdateBeveragePanel(PersonController personController, ProductController productController, Person employee, UUID id) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        BeverageDTO dto = BeverageDTO.toDTO(productController.searchById(id));

        JLabel titleLabel = new JLabel("Atualizar Bebida");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        JTextField codeField = new JTextField(dto.cod());
        JTextField nameField = new JTextField(dto.name());
        JTextField priceField = new JTextField(String.valueOf(dto.price()));
        JTextField amountField = new JTextField(String.valueOf(dto.amount()));
        JTextField expirationField = new JTextField(dto.expirationDate().toString());
        JTextField weightField = new JTextField(String.valueOf(dto.weight()));
        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado", dto.refrigerated());
        JTextField nutritionalInfoField = new JTextField(dto.nutritionalInfo());
        JTextField volumeField = new JTextField(String.valueOf(dto.volume()));
        JCheckBox alcoholicBox = new JCheckBox("Alcoólico", dto.alcoholic());
        JTextField flavorField = new JTextField(dto.flavor());
        JTextField brandField = new JTextField(dto.brand());

        add(AuxComponents.createHorizontalFields(
                "Código:", 14, codeField, 200, 25,
                "Nome:", 14, nameField, 400, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Preço:", 14, priceField, 200, 25,
                "Quantidade:", 14, amountField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Validade (AAAA-MM-DD):", 14, expirationField, 200, 25,
                "Peso:", 14, weightField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Informações Nutricionais:", 14, nutritionalInfoField, 300, 25,
                "Volume (ml):", 14, volumeField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Sabor:", 14, flavorField, 300, 25,
                "Marca:", 14, brandField, 300, 25
        ));
        add(Box.createVerticalStrut(10));

        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, alcoholicBox));

        add(Box.createVerticalStrut(20));

        JButton updateButton = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            try {
                BeverageDTO updatedDto = dto
                        .withCode(codeField.getText())
                        .withName(nameField.getText())
                        .withPrice(Double.parseDouble(priceField.getText()))
                        .withAmount(Integer.parseInt(amountField.getText()))
                        .withExpirationDate(LocalDate.parse(expirationField.getText()))
                        .withWeight(Double.parseDouble(weightField.getText()))
                        .withRefrigerated(refrigeratedBox.isSelected())
                        .withNutritionalInfo(nutritionalInfoField.getText())
                        .withVolume(Double.parseDouble(volumeField.getText()))
                        .withAlcoholic(alcoholicBox.isSelected())
                        .withFlavor(flavorField.getText())
                        .withBrand(brandField.getText());

                productController.updateBeverage(id, updatedDto);

                JOptionPane.showMessageDialog(null, "Bebida atualizada com sucesso!");

                SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, employee));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Certifique-se de que todos os campos numéricos estejam corretos.");
            }
        });

        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, employee)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        add(buttonPanel);
    }
}
