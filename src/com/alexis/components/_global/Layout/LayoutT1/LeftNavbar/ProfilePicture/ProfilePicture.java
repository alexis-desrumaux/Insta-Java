package com.alexis.components._global.Layout.LayoutT1.LeftNavbar.ProfilePicture;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class ProfilePicture extends com.alexis.common.Component.Component {
  private LayoutBuilder layoutBuilder;
  private JLabel title;
  private Rectangle titleRec;
  private Image logo;
  private Point logoPos;
  private ProfilePictureProps props;

  private void setStyleTitle() {
    this.title.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.titleRec.height));
    Font f = this.title.getFont();
    this.title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point titlePos = layoutBuilder.next(titleRec.width, titleRec.height,
        new Margin(20, 0, (int) LayoutHelper.getCenter(getWidth(), 0, this.titleRec.width, 0).getX(), 0));
    this.title.setBounds((int) titlePos.getX(), (int) titlePos.getY(), this.titleRec.width, this.titleRec.height);
    this.title.setForeground(Color.WHITE);
    this.title.setVisible(true);
  }

  private void initTitle() {
    this.title = new JLabel(Store.getInstance().getUser().getName() + " " + Store.getInstance().getUser().getSurname());
    this.titleRec = new Rectangle(0, 0, 0, 16);
    this.title.setVisible(false);
    this.add(this.title);
  }

  private void setStyleLogo() {
    this.logoPos = layoutBuilder.next(130, 130,
        new Margin((int) LayoutHelper.getCenter(0, this.getHeight(), 0, 130).getY(), 20,
            (int) LayoutHelper.getCenter(getWidth(), 0, 130, 0).getX(), 0));
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon(Store.getInstance().getUser().getPPPath());
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 270, 230);
    this.setForeground(Color.WHITE);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleLogo();
    this.setStyleTitle();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 400, 230, Color.RED);
    g2D.setPaint(gradient);
    g2D.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    if (this.titleRec.width == 0) {
      int width = g.getFontMetrics(this.title.getFont()).stringWidth(this.title.getText());
      this.titleRec.width = width;
      this.setStyleComponents();
    }

    g2D.setColor(Color.WHITE);
    g2D.fillRect(this.logoPos.x, this.logoPos.y, 129, 129);
    g2D.drawRect(this.logoPos.x, this.logoPos.y, 129, 129);

    g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
  }

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(ProfilePictureProps.TYPE_NAME)) {
        this.props = (ProfilePictureProps) props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ProfilePicture(String name, com.alexis.common.Component.Component parent, ProfilePictureProps props) {
    super(name, parent, 0, Color.WHITE);
    this.props = props;
    this.initClassAttributes();
    this.initLogo();
    this.initTitle();
    this.setStyleComponents();
  }
}
