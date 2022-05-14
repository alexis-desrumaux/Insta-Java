package com.alexis.common.RoundedPanel;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
  private Color backgroundColor;
  private int cornerRadius = 15;

  public void setBackgroundColor(Color bgColor) {
    backgroundColor = bgColor;
    revalidate();
    repaint();
  }

  public RoundedPanel(int radius) {
    super();
    cornerRadius = radius;
  }

  public RoundedPanel(int radius, Color bgColor) {
    super();
    cornerRadius = radius;
    backgroundColor = bgColor;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Dimension arcs = new Dimension(cornerRadius, cornerRadius);
    int width = getWidth();
    int height = getHeight();
    Graphics2D graphics = (Graphics2D) g;
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (backgroundColor != null) {
      graphics.setColor(backgroundColor);
    } else {
      graphics.setColor(getBackground());
    }
    graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    graphics.setColor(getForeground());
    graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
  }
}
