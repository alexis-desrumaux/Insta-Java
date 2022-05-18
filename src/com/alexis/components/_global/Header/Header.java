package com.alexis.components._global.Header;

import javax.swing.*;
import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.LayoutHelper.*;

public class Header extends com.alexis.common.Component.Component {
  private HeaderProps props;
  private Image logo;
  private Point logoPos;
  private LayoutBuilder layoutBuilder;

  private void setStyleLogo() {
    this.logoPos = layoutBuilder.next(273, 80,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 273, 0).getX(), 0));
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(273, 80, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.setFocusable(true);
    this.setLayout(null);
    this.setBounds((int) this.props.position.getX(), (int) this.props.position.getY(), Utils.SCREEN_WIDTH, 130);
    this.setOpaque(false);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStyleLogo();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 900, 130, Color.RED);
    g2D.setPaint(gradient);
    g2D.fillRect(0, 0, getWidth(), getHeight());
    g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
  }

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(HeaderProps.TYPE_NAME)) {
        this.props = (HeaderProps)props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Header(String name, com.alexis.common.Component.Component parent, HeaderProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initLogo();
    this.setStyleComponents();
  }
}
