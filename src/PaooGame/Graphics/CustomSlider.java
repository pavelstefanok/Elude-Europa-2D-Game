package PaooGame.Graphics;

import java.awt.geom.*;
import PaooGame.Input.Controls;
import java.awt.*;

public class CustomSlider {

    private int x, y, width, height;
    private int min, max;
    private int val;
    private int slider = 16;
    private boolean dragging= false;
    private float glowAlpha = 0.5f;
    private boolean glowIncreasing = true;


    public CustomSlider(int x, int y, int width, int height, int min, int max, int val) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.min = min;
        this.max = max;
        this.val = val;
    }

    public void Update() {

        int sliderX = getSliderX();
        int sliderY = y + height - 10;

        Ellipse2D SliderArie = new Ellipse2D.Double(sliderX - slider, sliderY - slider, slider * 2, slider * 2);
        if (!dragging && Controls.Mouse_drag && SliderArie.contains(Controls.Mouse_Position_x, Controls.Mouse_Position_y)) {
            dragging = true;
        }

        if (!Controls.Mouse_pressed) {
            dragging = false;
        }

        if (dragging) {

            int newX = Controls.Mouse_Position_x;
            if (newX < x)
                newX = x;
            if (newX > x + width)
                newX = x + width;

            double fraction = (double)(newX - x) / width;
            val = (int)(min + fraction * (max - min));
        }

        if (glowIncreasing) {
            glowAlpha += 0.009f;
            if (glowAlpha >= 0.9f)
                glowIncreasing = false;
        } else {
            glowAlpha -= 0.009f;
            if (glowAlpha < 0.5f)
                glowIncreasing = true;
        }
    }

    public void Draw(Graphics g, String name) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fundal animat
        g2.setColor(new Color(46, 14, 128, 100 + (int)(glowAlpha * 100)));
        g2.fillRoundRect(x - 10, y - 10, width + 20, height + 20, 20, 20);

        String label = name;
        Font labelFont = new Font("", Font.HANGING_BASELINE, 25);
        g2.setFont(labelFont);
        FontMetrics fm = g2.getFontMetrics(labelFont);
        int labelWidth = fm.stringWidth(label);
        g2.setColor(new Color(138, 229, 255));
        g2.drawString(label, x + (width - labelWidth) / 2, y + 20);

        // Bara slider
        g2.setColor(Color.LIGHT_GRAY);
        int lineY = y + height - 10;
        g2.setStroke(new BasicStroke(6));
        g2.drawLine(x + 5, lineY, x - 5 + width, lineY);

        int sliderX = getSliderX();
        int sliderY = lineY;

        // Glow efect
        int glowSize = slider * 3;
        g2.setColor(new Color(0, 255, 255, (int)(glowAlpha * 120)));
        g2.fillOval(sliderX - glowSize / 2, sliderY - glowSize / 2, glowSize, glowSize);

        //  slider
        g2.setColor(dragging ? new Color(75, 113, 163) : new Color(34, 13, 191));
        g2.fillOval(sliderX - slider , sliderY - slider, slider * 2, slider * 2);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(sliderX - slider, sliderY - slider, slider * 2, slider * 2);

        // Text
        g2.setFont(new Font("", Font.HANGING_BASELINE, 16));
        g2.setColor(new Color(138, 229, 255));
        String valStr = String.valueOf(val);
        FontMetrics font = g2.getFontMetrics();
        int strWidth = font.stringWidth(valStr);
        g2.drawString(valStr + "%", sliderX - strWidth / 2, sliderY - slider - 10);
        g2.dispose();
    }


    private int getSliderX() {
        double fraction = (double)(val - min) / (max - min);
        return x + (int)(fraction * width);
    }

    public int getVal() {
        return val;
    }
}

