package four;

import javax.swing.*;
import java.awt.*;

class PieChartPanel extends JPanel {
    private String[] labels;
    private int[] values;
    String currentStory;

    public PieChartPanel(String[] labels, int[] values) {
        this.labels = labels;
        this.values = values;

        this.currentStory = "Story: Implement Login Feature"; // Example story text
        setPreferredSize(new Dimension(500, 300)); // Make it a bit more compact vertically
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (values == null || values.length == 0) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        // draw the story box
        int padding = 20;
        int storyBoxWidth = getWidth() - 2 * padding;
        int storyBoxHeight = 50;
        int storyBoxX = padding;
        int storyBoxY = padding;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(storyBoxX, storyBoxY, storyBoxWidth, storyBoxHeight, 15, 15);

        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(storyBoxX, storyBoxY, storyBoxWidth, storyBoxHeight, 15, 15);

        FontMetrics fmCurr = g2d.getFontMetrics();
        int textWidthCurr = fmCurr.stringWidth(currentStory);
        int textHeightCurr = fmCurr.getAscent();

        g2d.drawString(currentStory, (getWidth() - textWidthCurr) / 2, storyBoxY + (storyBoxHeight + textHeightCurr) / 2 - 5);

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};

        int total = 0;
        for (int v : values) {
            total += v;
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 150;

        int startAngle = 0;
        double cumulativeAngle = 0;

        for (int i = 0; i < values.length; i++) {
            g2d.setColor(colors[i % colors.length]);

            double rawAngle = values[i] * 360.0 / total;
            int arcAngle;
            if (i == values.length - 1) {
                arcAngle = 360 - (int) Math.round(cumulativeAngle);
            } else {
                arcAngle = (int) Math.round(rawAngle);
                cumulativeAngle += arcAngle;
            }

            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, startAngle, arcAngle);

            // Draw label closer to center
            double midAngle = Math.toRadians(startAngle + arcAngle / 2.0);
            int labelRadius = radius - 50;
            int labelX = (int) (centerX + labelRadius * Math.cos(midAngle));
            int labelY = (int) (centerY + labelRadius * Math.sin(midAngle));

            g2d.setColor(Color.BLACK);
            String text = labels[i] + " (100%)"; // Label like "5 (100%)"

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            g2d.drawString(text, labelX - textWidth / 2, labelY + textHeight / 4);

            startAngle += arcAngle;
        }
    }

    // In PieChartPanel
    public void setStory(String story) {
        this.currentStory = story;
    }
}
