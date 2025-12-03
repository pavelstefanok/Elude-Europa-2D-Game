package PaooGame.Graphics;

import java.awt.*;

public class CustomButton {
    public static void DrawButton(Graphics g, String text,Rectangle button, boolean hover) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (hover) {
            g2.setColor(new Color(0, 255, 255, 100));
            g2.fillRoundRect(button.x + 5, button.y + 5, button.width - 10, button.height - 10, 30, 30);
        }

        // background
        g2.setColor(hover ? new Color(99, 168, 165) : new Color(26, 118, 161,190));
        g2.fillRoundRect(button.x, button.y, button.width, button.height, 30, 30);

        g2.setColor(new Color(6, 39, 54));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(button.x, button.y, button.width - 1, button.height- 1, 30, 30);

        // text
        g2.setFont(new Font("", Font.HANGING_BASELINE, 32));
        FontMetrics font = g2.getFontMetrics();
        int textX = button.x + (button.width - font.stringWidth(text)) / 2;
        int textY = button.y + (button.height + font.getAscent()) / 2 - 5;
        g2.setColor(new Color(4, 36, 51));
        g2.drawString(text, textX, textY);
    }
}