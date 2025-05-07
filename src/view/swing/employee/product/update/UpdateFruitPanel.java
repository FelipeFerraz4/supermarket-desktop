package view.swing.employee.product.update;

import controllers.PersonController;
import controllers.ProductController;
import dtos.FruitDTO;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SearchProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class UpdateFruitPanel extends JPanel {
    public UpdateFruitPanel(PersonController personController, ProductController productController, Person employee, UUID id) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        FruitDTO dto = FruitDTO.toDTO(productController.searchById(id));

        JLabel titleLabel = new JLabel("Atualizar Fruta");
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
        JTextField varietyField = new JTextField(dto.variety());
        JTextField originField = new JTextField(dto.origin());
        JCheckBox seasonalBox = new JCheckBox("Sazonal", dto.seasonal());
        JTextField packagingTypeField = new JTextField(dto.packagingType());

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
                "Variedade:", 14, varietyField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Origem:", 14, originField, 300, 25,
                "Tipo de Embalagem:", 14, packagingTypeField, 300, 25
        ));

        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, seasonalBox));
        add(Box.createVerticalStrut(20));

        JButton updateButton = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            FruitDTO updatedDto = dto
                    .withCode(codeField.getText())
                    .withName(nameField.getText())
                    .withPrice(Double.parseDouble(priceField.getText()))
                    .withAmount(Integer.parseInt(amountField.getText()))
                    .withExpirationDate(LocalDate.parse(expirationField.getText()))
                    .withWeight(Double.parseDouble(weightField.getText()))
                    .withRefrigerated(refrigeratedBox.isSelected())
                    .withNutritionalInfo(nutritionalInfoField.getText())
                    .withVariety(varietyField.getText())
                    .withOrigin(originField.getText())
                    .withSeasonal(seasonalBox.isSelected())
                    .withPackagingType(packagingTypeField.getText());

            productController.updateFruit(id, updatedDto);
            JOptionPane.showMessageDialog(null, "Fruta atualizada com sucesso!");
            SwingMenu.changeScreen(new UpdateFruitPanel(personController, productController, employee, id));
        });

        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, employee)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        add(buttonPanel);
    }
}
