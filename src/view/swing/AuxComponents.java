package view.swing;

import javax.swing.*;
import java.awt.*;

public class AuxComponents {

    public static JButton createStyledButton(String text, int width, int height, Runnable action) {
        try {
            if (text == null || action == null) {
                throw new IllegalArgumentException("Texto ou ação não pode ser nulo.");
            }

            JButton button = new JButton(text);
            button.setBackground(Color.GRAY);
            button.setForeground(Color.WHITE);

            Dimension buttonSize = new Dimension(width, height);
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);

            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.addActionListener(_ -> action.run());

            return button;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o botão: " + e.getMessage(),
                    "Erro de Criação de Botão", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static JPanel createHorizontalButtonPanel(JButton... buttons) {
        try {
            if (buttons == null || buttons.length == 0) {
                throw new IllegalArgumentException("Os botões não podem ser nulos ou vazios.");
            }

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            int totalWidth = 0;
            int maxHeight = 0;
            int spacing = 20;

            for (int i = 0; i < buttons.length; i++) {
                JButton btn = buttons[i];
                if (btn == null) {
                    throw new IllegalArgumentException("Botão no índice " + i + " é nulo.");
                }
                Dimension size = btn.getPreferredSize();
                totalWidth += size.width;
                maxHeight = Math.max(maxHeight, size.height);

                panel.add(btn);

                if (i < buttons.length - 1) {
                    panel.add(Box.createHorizontalStrut(spacing));
                    totalWidth += spacing;
                }
            }

            totalWidth += 20; // margem extra lateral

            Dimension panelSize = new Dimension(totalWidth, maxHeight + 10);
            panel.setPreferredSize(panelSize);
            panel.setMaximumSize(panelSize);
            panel.setMinimumSize(panelSize);

            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            return panel;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar painel de botões: " + e.getMessage(),
                    "Erro de Criação de Painel", JOptionPane.ERROR_MESSAGE);
            return new JPanel();
        }
    }

    public static JPanel createLabeledField(String labelText, int fontsize, JComponent field, int fieldWidth, int fieldHeight) {
        try {
            if (labelText == null || field == null) {
                throw new IllegalArgumentException("Texto do rótulo ou campo não pode ser nulo.");
            }

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Para alinhar

            // Label
            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Arial", Font.BOLD, fontsize));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);

            Dimension dimension = new Dimension(fieldWidth, fieldHeight);

            // Campo
            field.setAlignmentX(Component.LEFT_ALIGNMENT);
            field.setMinimumSize(dimension);
            field.setMaximumSize(dimension);
            field.setPreferredSize(dimension);

            panel.add(label);
            panel.add(Box.createVerticalStrut(5));
            panel.add(field);

            panel.setMaximumSize(new Dimension(fieldWidth, 80)); // Limita o painel no mesmo tamanho do field
            return panel;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o campo rotulado: " + e.getMessage(),
                    "Erro de Criação de Campo", JOptionPane.ERROR_MESSAGE);
            return new JPanel();
        }
    }

    public static JPanel createHorizontalFields(
            String labelText1, int labelFontSize1, JComponent field1, int fieldWidth1, int fieldHeight1,
            String labelText2, int labelFontSize2, JComponent field2, int fieldWidth2, int fieldHeight2
    ) {
        try {
            if (labelText1 == null || field1 == null || labelText2 == null || field2 == null) {
                throw new IllegalArgumentException("Texto ou campo não pode ser nulo.");
            }

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            panel.add(createLabeledField(labelText1, labelFontSize1, field1, fieldWidth1, fieldHeight1));
            panel.add(Box.createHorizontalStrut(20));
            panel.add(createLabeledField(labelText2, labelFontSize2, field2, fieldWidth2, fieldHeight2));

            int totalWidth = fieldWidth1 + fieldWidth2 + 20;
            panel.setPreferredSize(new Dimension(totalWidth, 80));
            panel.setMaximumSize(new Dimension(totalWidth, 80));
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);
            return panel;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar campos horizontais: " + e.getMessage(),
                    "Erro de Criação de Campos", JOptionPane.ERROR_MESSAGE);
            return new JPanel();
        }
    }

    public static JPanel createHorizontalCheckBoxes(JCheckBox... checkBoxes) {
        try {
            if (checkBoxes == null || checkBoxes.length == 0) {
                throw new IllegalArgumentException("Os checkboxes não podem ser nulos ou vazios.");
            }

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i] == null) {
                    throw new IllegalArgumentException("Checkbox no índice " + i + " é nulo.");
                }
                panel.add(checkBoxes[i]);
                if (i < checkBoxes.length - 1) {
                    panel.add(Box.createHorizontalStrut(20)); // espaçamento entre os checkboxes
                }
            }

            return panel;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar checkboxes: " + e.getMessage(),
                    "Erro de Criação de Checkboxes", JOptionPane.ERROR_MESSAGE);
            return new JPanel();
        }
    }

    public static JButton createSafeStyledButton(Component parentComponent, String text, int width, int height, Runnable action, String errorMessagePrefix) {
        return createStyledButton(text, width, height, () -> {
            try {
                action.run();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parentComponent,
                        errorMessagePrefix + ": " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
//                e.printStackTrace();
            }
        });
    }

}
