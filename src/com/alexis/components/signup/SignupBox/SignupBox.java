package com.alexis.components.signup.SignupBox;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.Component.*;
import com.alexis.common.Components.Components;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.RoundedPanel.RoundedPanel;
import com.alexis.store.Store;

public class SignupBox extends com.alexis.common.Component.Component {
  private JLabel cTitle;
  private Image logo;
  private Point logoPos;
  private JTextField emailField;
  private JPasswordField pwdField;
  private JButton signupBtn;
  private JButton signinBtn;
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void initSigninButton() {
    this.signinBtn = new JButton("Click here to log in");
    Point location = layoutBuilder.next(350, 16,
        new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.signinBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 16);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, 16).deriveFont(fontAttributes);
    this.signinBtn.setFont(plainUnderline);
    this.signinBtn.setForeground(Color.BLUE);
    this.signinBtn.setOpaque(false);
    this.signinBtn.setContentAreaFilled(false);
    this.signinBtn.setBorderPainted(false);
    this.signinBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("CLICK");
        Store.getInstance().getApp().getSigninFrame().changingToSignin();
      }
    });
    this.panel.add(this.signinBtn);
  }

  private void initSignupButton() {
    this.signupBtn = new JButton("Sign up");
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.signupBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.signupBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.signupBtn.setBackground(Color.BLUE);
    this.signupBtn.setForeground(Color.WHITE);
    this.panel.add(this.signupBtn);
  }

  private void initPwdField() {
    this.pwdField = new JPasswordField();
    Point pField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.pwdField.setBounds((int) pField.getX(), (int) pField.getY(), 350, 50);
    this.pwdField.setText("Password");
    this.pwdField.setForeground(new Color(171, 171, 171));
    this.pwdField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.pwdField.setBorder(BorderFactory.createCompoundBorder(
        this.pwdField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.panel.add(this.pwdField);
  }

  private void initEmailField() {
    this.emailField = new JTextField();
    Point eField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.emailField.setBounds((int) eField.getX(), (int) eField.getY(), 350, 50);
    this.emailField.setText("E-mail");
    this.emailField.setForeground(new Color(171, 171, 171));
    this.emailField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.emailField.setBorder(BorderFactory.createCompoundBorder(
        this.emailField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.panel.add(this.emailField);
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(341, 100, Image.SCALE_SMOOTH);
      this.logoPos = layoutBuilder.next(341, 100,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 341, 0).getX(), 0));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void initTitle() {

    this.cTitle = new JLabel("Welcome to SKOC!");
    this.cTitle.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 30));
    Font f = this.cTitle.getFont();
    this.cTitle.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point cTitlePos = layoutBuilder.next(320, 30,
        new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 320, 0).getX(), 0));
    this.cTitle.setBounds((int) cTitlePos.getX(), (int) cTitlePos.getY(), 320, 30);
    this.panel.add(this.cTitle);
  }

  public SignupBox(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel = new SignupBoxPanel(30, Color.WHITE);
    this.panel.setLayout(null);
    this.panel.setBounds((Utils.SCREEN_WIDTH / 2) - (700 / 2), (Utils.SCREEN_HEIGHT / 2) - (550 / 2), 700, 550);
    this.panel.setOpaque(false);
    this.panel.setFocusable(true);
    this.initLogo();
    this.initTitle();
    this.initEmailField();
    this.initPwdField();
    this.initSignupButton();
    this.initSigninButton();
  }

  class SignupBoxPanel extends RoundedPanel {

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
    }

    public SignupBoxPanel(int radius, Color bgColor) {
      super(radius, bgColor);
    }

    public SignupBoxPanel(int radius) {
      super(radius);
    }
  }

}

