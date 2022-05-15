package com.alexis.components._global.Header;

import javax.swing.*;
import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.Components.Components;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.LayoutHelper.*;

public class Header extends com.alexis.common.Component.Component {
  private Image logo;
  private Point logoPos;
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(273, 80, Image.SCALE_SMOOTH);
      this.logoPos = layoutBuilder.next(273, 80,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 273, 0).getX(), 0));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void initClassAttributes(Point pos) {
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel = new HeaderPanel();
    this.panel.setBounds((int) pos.getX(), (int) pos.getY(), Utils.SCREEN_WIDTH, 130);
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    this.panel.setOpaque(false);
  }

  public Header(String name, Components parent, Point pos) {
    super(name, parent);
    this.initClassAttributes(pos);
    this.initLogo();
  }

  public class HeaderPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 900, 130, Color.RED);
      g2D.setPaint(gradient);
      g2D.fillRect(0, 0, getWidth(), getHeight());
      g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
    }
  }
}
