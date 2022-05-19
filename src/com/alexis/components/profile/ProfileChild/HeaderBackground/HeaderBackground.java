package com.alexis.components.profile.ProfileChild.HeaderBackground;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.store.User.USER_TYPE;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class HeaderBackground extends com.alexis.common.Component.Component {
  public HeaderBackgroundProps props;
  public LayoutBuilder layoutBuilder;
  private Image bg;
  private Point bgPos;
  private JLabel name;
  private Rectangle nameRec;
  private JLabel nname;
  private Rectangle nnameRec;
  private Image pp;
  private Point ppPos;
  private Image premiumIco;
  private Point premiumIcoPos;
  private JButton logoutBtn;

  private void handleOnClickLogout() {
    Store.getInstance().setUser(null);
    Store.getInstance().getApp().displaySigninFrame();
  }

  private void setStyleLogoutButton() {
    this.logoutBtn.setBounds(this.getWidth() - 80 - 50, this.getHeight() - 30 - 50, 80, 30);
    this.logoutBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 14));
    this.logoutBtn.setBackground(Color.RED);
    this.logoutBtn.setForeground(Color.WHITE);
    this.logoutBtn.setMargin(new Insets(0, 0, 0, 0));
    this.logoutBtn.setBorderPainted(false);
    this.logoutBtn.setVisible(true);
  }

  private void initLogoutButton() {
    this.logoutBtn = new JButton("Log out");
    this.logoutBtn.setEnabled(true);

    this.logoutBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickLogout();
      }
    });
    this.logoutBtn.setVisible(false);
    this.add(this.logoutBtn);
  }

  private void setStylePremiumIco() {
    if (Store.getInstance().getUser().getUserType().equals(User.USER_TYPE.PREMIUM)) {
      this.premiumIcoPos = layoutBuilder.next(25, 25, new Margin(10, 0, 10, 0));
    }
  }

  private void initPremiumIco() {
    if (Store.getInstance().getUser().getUserType().equals(User.USER_TYPE.PREMIUM)) {
      try {
        ImageIcon ii = new ImageIcon("src" + File.separator + "com" + File.separator + "alexis" + File.separator
            + "assets" + File.separator + "premium-ico.png");
        this.premiumIco = ii.getImage();
        this.premiumIco = this.premiumIco.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  private void setStyleNName() {
    this.layoutBuilder.setPosition(new Point(this.name.getX(), this.name.getY() + this.name.getHeight()));
    this.nname.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.nnameRec.height));
    Font f = this.nname.getFont();
    this.nname.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point titlePos = layoutBuilder.next(nnameRec.width + 10, nnameRec.height +
        10,
        new Margin(10, 0, 0, 0));
    this.nname.setBounds((int) titlePos.getX(), (int) titlePos.getY(), this.nnameRec.width + 10,
        this.nnameRec.height + 10);
    this.nname.setForeground(Color.WHITE);
    this.nname.setBackground(new Color(33, 33, 33));
    this.nname.setBorder(new EmptyBorder(0, 5, 0, 0));
    this.nname.setOpaque(true);
    this.nname.setVisible(true);
  }

  private void initNickName() {
    this.nname = new JLabel(Store.getInstance().getUser().getNickName());
    this.nnameRec = new Rectangle(0, 0, 0, 12);
    this.nname.setVisible(false);
    this.add(this.nname);
  }

  private void setStyleName() {
    this.name.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.nameRec.height));
    Font f = this.name.getFont();
    this.name.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point titlePos = layoutBuilder.next(nameRec.width + 10, nameRec.height +
        10,
        new Margin(230 - 100 + nameRec.height, 0, 20, 0));
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    this.name.setBounds((int) titlePos.getX(), (int) titlePos.getY(), this.nameRec.width + 10,
        this.nameRec.height + 10);
    this.name.setForeground(Color.WHITE);
    this.name.setBackground(new Color(33, 33, 33));
    this.name.setBorder(new EmptyBorder(0, 5, 0, 0));
    this.name.setOpaque(true);
    this.name.setVisible(true);
  }

  private void initName() {
    this.name = new JLabel(Store.getInstance().getUser().getName() + " " + Store.getInstance().getUser().getSurname());
    this.nameRec = new Rectangle(0, 0, 0, 16);
    this.name.setVisible(false);
    this.add(this.name);
  }

  private void setStylePP() {
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    this.ppPos = layoutBuilder.next(100, 100, new Margin(230 - 100 - 20, 0, 20, 0));
  }

  private void initPP() {
    try {
      ImageIcon ii = new ImageIcon(Store.getInstance().getUser().getPPPath());
      this.pp = ii.getImage();
      this.pp = this.pp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setStyleBG() {
    this.bgPos = new Point(0, 0);
  }

  public void initBG() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/background-profile.jpg");
      this.bg = ii.getImage();
      this.bg = this.bg.getScaledInstance(800, 230, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 230);
    this.setForeground(Color.WHITE);
    this.setVisible(true);
  }

  public void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleBG();
    this.setStylePP();
    this.setStyleName();
    this.setStyleNName();
    this.setStylePremiumIco();
    this.setStyleLogoutButton();
  }

  public void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    if (this.nameRec.width == 0) {
      int widthName = g.getFontMetrics(this.name.getFont()).stringWidth(this.name.getText());
      this.nameRec.width = widthName;
      int widthNName = g.getFontMetrics(this.nname.getFont()).stringWidth(this.nname.getText());
      this.nnameRec.width = widthNName;
      this.setStyleComponents();
    }

    g2D.drawImage(this.bg, (int) this.bgPos.getX(), (int) this.bgPos.getY(), this);
    g2D.drawImage(this.pp, (int) this.ppPos.getX(), (int) this.ppPos.getY(), this);

    if (Store.getInstance().getUser().getUserType().equals(USER_TYPE.PREMIUM))
      g2D.drawImage(this.premiumIco, (int) this.premiumIcoPos.getX(), (int) this.premiumIcoPos.getY(), this);
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(HeaderBackgroundProps.TYPE_NAME)) {
        this.props = (HeaderBackgroundProps) props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public HeaderBackground(String name, com.alexis.common.Component.Component parent, HeaderBackgroundProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initBG();
    this.initPP();
    this.initName();
    this.initNickName();
    this.initPremiumIco();
    this.initLogoutButton();
    this.setStyleComponents();
  }
}
