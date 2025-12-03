package UI.common;

import java.awt.*;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    public RoundedButton(String text, int par) {
        super(text);
        setContentAreaFilled(false);   // ما يلونّش الخلفية المستطيلة العادية
        setFocusPainted(false);        // يشيل حافة الفوكس الزرقا
        setBorderPainted(false);       // ما يرسمش البوردر العادي
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // تنعيم الحواف
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int width  = getWidth();
        int height = getHeight();

        // لون الخلفية
        g2.setColor(new Color(44, 44, 44));   // تقدر تغيره لأي لون
        int arc = 30;                        // كل ما تكبره الزرار يبقى أروح
        g2.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);

        // رسم النص في النص
        FontMetrics fm = g2.getFontMetrics();
        int textX = (width  - fm.stringWidth(getText())) / 2;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

}
