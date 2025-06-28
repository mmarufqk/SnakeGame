package utils;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Window;
import java.awt.BorderLayout;

public class CustomConfirmDialog extends JDialog {
    private boolean confirmed = false;

    public CustomConfirmDialog(Window parent, String message, String title) {
        this(parent, message, title, true);
    }

    public CustomConfirmDialog(Window parent, String message, String title, boolean showNoButton) {
        super(parent, title, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        utils.FontLoader.applyFont(label, 16f);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        utils.FontLoader.applyFont(yesButton, 14f);
        buttonPanel.add(yesButton);
        if (showNoButton) {
            JButton noButton = new JButton("No");
            utils.FontLoader.applyFont(noButton, 14f);
            buttonPanel.add(noButton);
            noButton.addActionListener(e -> {
                confirmed = false;
                dispose();
            });
        }
        add(buttonPanel, BorderLayout.SOUTH);

        yesButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean showDialog() {
        setVisible(true);
        return confirmed;
    }
}
