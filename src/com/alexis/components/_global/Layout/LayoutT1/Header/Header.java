package com.alexis.components._global.Layout.LayoutT1.Header;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class Header extends com.alexis.common.Component.Component {
  private HeaderProps props;
  private Image logo;
  private Point logoPos;
  private boolean isLogoVisible;
  private JButton profileBtn;
  private Rectangle profileRec;
  private LayoutBuilder layoutBuilder;

  private void setStyleProfile() {
    Point posO = layoutBuilder.next(0, 0);
    Point pos = layoutBuilder.next(this.profileRec.width, this.profileRec.height,
        new Margin((int) LayoutHelper.getCenter(0, this.getHeight(), 0, this.profileRec.height).getY(), 0,
            this.getWidth() - (int)posO.getX() - this.profileRec.width - 40 , 0));
    this.profileBtn.setBounds((int) pos.getX(), (int) pos.getY(), this.profileRec.width, this.profileRec.height);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, this.profileRec.height).deriveFont(fontAttributes);
    this.profileBtn.setFont(plainUnderline);
    this.profileBtn.setForeground(Color.WHITE);
    this.profileBtn.setOpaque(false);
    this.profileBtn.setContentAreaFilled(false);
    this.profileBtn.setBorderPainted(false);
    this.profileBtn.setMargin(new Insets(0, 0, 0, 0));
    this.profileBtn.setBorder(null);
    this.profileBtn.setVisible(true);
  }

  private void initProfile() {
    this.profileBtn = new JButton(Store.getInstance().getUser().getNickName());
    this.profileRec = new Rectangle(0, 0, 0, 16);
    this.profileBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("CLICK");
      }
    });
    this.profileBtn.setVisible(false);
    this.add(this.profileBtn);
  }

  private void setStyleLogo() {
    this.logoPos = layoutBuilder.next(102, 30,
        new Margin((int) LayoutHelper.getCenter(0, this.getHeight(), 0, 30).getY(), 0, 20, 0));
    this.isLogoVisible = true;
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(102, 30, Image.SCALE_SMOOTH);
      this.isLogoVisible = false;
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStyleComponents() {
    this.layoutBuilder.setPosition(new Point(0, 0));
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    this.setStyleLogo();
    this.setStyleProfile();
  }

  public void initClassAttributes(HeaderProps props) {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.HORIZONTAL_ALIGN);
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds((int) props.position.getX(), (int) props.position.getY(), Utils.SCREEN_WIDTH, 60);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 900, 60, Color.RED);
    g2D.setPaint(gradient);
    g2D.fillRect(0, 0, getWidth(), getHeight());

    int width = g.getFontMetrics(profileBtn.getFont()).stringWidth(profileBtn.getText());
    this.profileRec.width = width;
    this.setStyleComponents();
    if (this.isLogoVisible)
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
    this.initClassAttributes(props);
    this.initLogo();
    this.initProfile();
    this.setStyleComponents();
  }
}
