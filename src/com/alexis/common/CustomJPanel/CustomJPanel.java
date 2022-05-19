package com.alexis.common.CustomJPanel;

import java.awt.*;
import javax.swing.*;

public class CustomJPanel extends JPanel {
  private Color backgroundColor;
  private int cornerRadius = 15;
  private int borderSize = 1;
  private boolean isDisabled;

  public void setBackgroundColor(Color bgColor) {
    backgroundColor = bgColor;
    revalidate();
    repaint();
  }

  public CustomJPanel(boolean isDisabled) {
    this.isDisabled = isDisabled;
  }

  public CustomJPanel(int radius, Color bgColor, int borderSize) {
    super();
    cornerRadius = radius;
    backgroundColor = bgColor;
    this.borderSize = borderSize;
    this.isDisabled = false;
  }

  public CustomJPanel(int radius) {
    super();
    cornerRadius = radius;
    this.isDisabled = false;
  }

  public CustomJPanel(int radius, Color bgColor) {
    super();
    cornerRadius = radius;
    backgroundColor = bgColor;
    this.isDisabled = false;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (this.isDisabled)
      return;
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
    graphics.fillRoundRect(0, 0, width - borderSize, height - borderSize, arcs.width, arcs.height);
    graphics.setColor(getForeground());
    graphics.drawRoundRect(0, 0, width - borderSize, height - borderSize, arcs.width, arcs.height);
  }
}
