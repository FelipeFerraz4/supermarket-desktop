package view.swing;

import javax.swing.*;
import java.awt.*;

public class AuxComponents {

    public static JButton createStyledButton(String text, int width, int height, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);

        Dimension buttonSize = new Dimension(width, height);
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(e -> action.run());

        return button;
    }

    public static JPanel createHorizontalButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        for (int i = 0; i < buttons.length; i++) {
            panel.add(buttons[i]);
            if (i < buttons.length - 1) {
                panel.add(Box.createHorizontalStrut(10)); // Espaço entre os botões
            }
        }

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }


    public static JPanel createLabeledField(String labelText, int fontsize, JComponent field, int fieldWidth, int fieldHeight) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Para alinhar

        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, fontsize));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        Dimension dimension = new Dimension(fieldWidth, fieldHeight);

        // Campo
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMinimumSize(dimension);
        field.setMaximumSize(dimension);
        field.setPreferredSize(dimension);  // <-- adiciona também preferred

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);

        panel.setMaximumSize(new Dimension(fieldWidth, 80)); // Limita o painel no mesmo tamanho do field
        return panel;
    }


    public static JPanel createHorizontalFields(
            String labelText1, int labelFontSize1, JComponent field1, int fieldWidth1, int fieldHeight1,
            String labelText2, int labelFontSize2, JComponent field2, int fieldWidth2, int fieldHeight2
    ) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(createLabeledField(labelText1, labelFontSize1, field1, fieldWidth1, fieldHeight1));
        panel.add(Box.createHorizontalStrut(20));
        panel.add(createLabeledField(labelText2, labelFontSize2, field2, fieldWidth2, fieldHeight2));

        panel.setMaximumSize(new Dimension(600, 80)); // Opcional: limita largura total da linha
        return panel;
    }

}
