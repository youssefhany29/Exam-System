package UI.common;

import java.awt.*;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private Color bgColor = new Color(44, 44, 44);  
    private Color textColor = Color.WHITE;         
    private int radius = 30;       

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public void setBackgroundColor(Color color) {
        this.bgColor = color;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int width  = getWidth();
        int height = getHeight();

        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, width, height, radius, radius);

        g2.setColor(textColor);
        FontMetrics fm = g2.getFontMetrics();
        int textX = (width  - fm.stringWidth(getText())) / 2;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }
}
