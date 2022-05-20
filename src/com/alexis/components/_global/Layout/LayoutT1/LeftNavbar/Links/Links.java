package com.alexis.components._global.Layout.LayoutT1.LeftNavbar.Links;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class Links extends com.alexis.common.Component.Component {
  private LinksProps props;
  private LayoutBuilder layoutBuilder;
  private Image homeIco;
  private Point homeIcoPos;
  private Image profileIco;
  private Point profileIcoPos;
  private JButton homeBtn;
  private JButton profileBtn;

  private void setStyleProfileButton() {
    Point location = layoutBuilder.next(65, 16,
        new Margin(25, 0, 20, 0));
    this.profileBtn.setBounds((int) location.getX(), (int) location.getY(), 65, 16);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, 16).deriveFont(fontAttributes);
    this.profileBtn.setFont(plainUnderline);
    this.profileBtn.setForeground(Color.BLACK);
    this.profileBtn.setOpaque(false);
    this.profileBtn.setContentAreaFilled(false);
    this.profileBtn.setBorderPainted(false);
    this.profileBtn.setMargin(new Insets(0, 0, 0, 0));
    this.profileBtn.setVisible(true);
  }

  private void initProfileButton() {
    this.profileBtn = new JButton("Profile");
    this.profileBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Store.getInstance().getApp().getMainFrame().changingToProfile();
      }
    });
    this.profileBtn.setVisible(false);
    this.add(this.profileBtn);
  }

  private void setStyleProfileIco() {
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point p = this.layoutBuilder.next(0, 0);
    this.layoutBuilder.setPosition(new Point(0, p.y));
    this.profileIcoPos = layoutBuilder.next(20, 20,
        new Margin(20, 0, 20, 0));
  }

  private void initProfileIco() {
    try {
      ImageIcon ii = new ImageIcon("src" + File.separator + "com" + File.separator + "alexis" + File.separator + "assets" + File.separator + "profile.png");
      this.profileIco = ii.getImage();
      this.profileIco = this.profileIco.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStyleHomeButton() {
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point location = layoutBuilder.next(65, 16,
        new Margin(25, 0, 20, 0));
    this.homeBtn.setBounds((int) location.getX(), (int) location.getY(), 65, 16);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, 16).deriveFont(fontAttributes);
    this.homeBtn.setFont(plainUnderline);
    this.homeBtn.setForeground(Color.BLACK);
    this.homeBtn.setOpaque(false);
    this.homeBtn.setContentAreaFilled(false);
    this.homeBtn.setBorderPainted(false);
    this.homeBtn.setMargin(new Insets(0, 0, 0, 0));
    this.homeBtn.setVisible(true);
  }

  private void initHomeButton() {
    this.homeBtn = new JButton("Home");
    this.homeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    this.homeBtn.setVisible(false);
    this.add(this.homeBtn);
  }

  private void setStyleHomeIco() {
    this.homeIcoPos = layoutBuilder.next(20, 20,
        new Margin(20, 0, 20, 0));
  }

  private void initHomeIco() {
    try {
      ImageIcon ii = new ImageIcon("src" + File.separator + "com" + File.separator + "alexis" + File.separator + "assets" + File.separator + "home.png");
      this.homeIco = ii.getImage();
      this.homeIco = this.homeIco.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 270, 100);
    this.setForeground(Color.WHITE);
    this.setVisible(true);
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.HORIZONTAL_ALIGN);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.HORIZONTAL_ALIGN);
    this.setStylePanel();
    this.setStyleHomeIco();
    this.setStyleHomeButton();
    this.setStyleProfileIco();
    this.setStyleProfileButton();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    g2D.drawImage(this.homeIco, (int) this.homeIcoPos.getX(), (int) this.homeIcoPos.getY(), this);
    g2D.drawImage(this.profileIco, (int) this.profileIcoPos.getX(), (int) this.profileIcoPos.getY(), this);
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(LinksProps.TYPE_NAME)) {
        this.props = (LinksProps) newProps;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Links(String name, com.alexis.common.Component.Component parent, LinksProps props) {
    super(name, parent, 0, Color.WHITE);
    this.props = props;
    this.initClassAttributes();
    this.initHomeIco();
    this.initHomeButton();
    this.initProfileIco();
    this.initProfileButton();
    this.setStyleComponents();
  }
}
