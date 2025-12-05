package UI.admin.panel;

import java.awt.*;
import javax.swing.*;

public class InfoCard extends JPanel {

    private final JLabel iconLabel;
    private final JLabel titleLabel;
    private final JLabel valueLabel;
    private final Color bgColor;

    public InfoCard(String title, int number, String iconPath, Color bgColor) {

        setLayout(new GridBagLayout());
        setOpaque(false);
        this.bgColor = bgColor;

        // === Load Icon ===
        iconLabel = new JLabel();
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(iconPath);

            // نضبط الحجم لو عايزها أصغر شويه
            Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(scaled));
        }

        // === Title Label ===
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        // === Value Label ===
        valueLabel = new JLabel(String.valueOf(number));
        valueLabel.setFont(new Font("Inter", Font.BOLD, 20));
        valueLabel.setForeground(Color.WHITE);

        // === Add components vertically ===
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridy = 0;
        add(iconLabel, gbc);

        gbc.gridy = 1;
        add(titleLabel, gbc);

        gbc.gridy = 2;
        add(valueLabel, gbc);

        setPreferredSize(new Dimension(180, 140));
    }

    // === Rounded background ===
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();
    }

    // === Update number ===
    public void setValue(int newValue) {
        valueLabel.setText(String.valueOf(newValue));
        repaint();
    }

    // === Update icon if needed ===
    public void setIcon(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaled));
        repaint();
    }
}
