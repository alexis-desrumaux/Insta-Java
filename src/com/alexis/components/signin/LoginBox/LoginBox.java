package com.alexis.components.signin.LoginBox;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.Component.*;
import com.alexis.common.Components.Components;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.RoundedPanel.RoundedPanel;

public class LoginBox extends com.alexis.common.Component.Component {
  private JLabel cTitle;
  private Image logo;
  private Point logoPos;
  private JTextField nickNameField;
  private JPasswordField pwdField;
  private JButton signinBtn;
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void initSigninButton() {
    this.signinBtn = new JButton("Sign in");
    Point location = layoutBuilder.next(350, 50, new Margin(40, 0, (int)LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.signinBtn.setBounds((int)location.getX(), (int)location.getY(), 350, 50);
    this.signinBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.signinBtn.setBackground(Color.BLUE);
    this.signinBtn.setForeground(Color.WHITE);
    this.panel.add(this.signinBtn);
  }

  private void initPwdField() {
    this.pwdField = new JPasswordField();
    Point pField = layoutBuilder.next(350, 50, new Margin(40, 0, (int)LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.pwdField.setBounds((int)pField.getX(), (int)pField.getY(), 350, 50);
    this.pwdField.setText("Password");
    this.pwdField.setForeground(new Color(171, 171, 171));
    this.pwdField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.pwdField.setBorder(BorderFactory.createCompoundBorder(
        this.pwdField.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.panel.add(this.pwdField);
  }

  private void initNickNameField() {
    this.nickNameField = new JTextField();
    Point nField = layoutBuilder.next(350, 50, new Margin(40, 0, (int)LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.nickNameField.setBounds((int)nField.getX(), (int)nField.getY(), 350, 50);
    this.nickNameField.setText("Username");
    this.nickNameField.setForeground(new Color(171, 171, 171));
    this.nickNameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nickNameField.setBorder(BorderFactory.createCompoundBorder(
        this.nickNameField.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.panel.add(this.nickNameField);
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(341, 100, Image.SCALE_SMOOTH);
      this.logoPos = layoutBuilder.next(341, 100, new Margin(20, 0, (int)LayoutHelper.getCenter(this.panel.getBounds().width, 0, 341, 0).getX(), 0));
      
    } catch (Exception e) {
        System.out.println(e);
    }
  }

  private void initTitle() {

    this.cTitle = new JLabel("Welcome to SKOC!");
    this.cTitle.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 30));
    Font f = this.cTitle.getFont();
    this.cTitle.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point cTitlePos = layoutBuilder.next(320, 30, new Margin(20, 0, (int)LayoutHelper.getCenter(this.panel.getBounds().width, 0, 320, 0).getX(), 0));
    this.cTitle.setBounds((int) cTitlePos.getX(), (int)cTitlePos.getY(), 320, 30);
    this.panel.add(this.cTitle);
  }

  public LoginBox(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel = new LoginBoxPanel(30, Color.WHITE);
    this.panel.setLayout(null);
    this.panel.setBounds((Utils.SCREEN_WIDTH / 2) - (700 / 2), (Utils.SCREEN_HEIGHT / 2) - (550 / 2), 700, 550);
    this.panel.setOpaque(false);
    this.panel.setFocusable(true);
    this.initLogo();
    this.initTitle();
    this.initNickNameField();
    this.initPwdField();
    this.initSigninButton();
  }
  class LoginBoxPanel extends RoundedPanel {

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      g2D.drawImage(logo, (int)logoPos.getX(), (int)logoPos.getY(), this);
    }

    public LoginBoxPanel(int radius, Color bgColor) {
      super(radius, bgColor);
    }

    public LoginBoxPanel(int radius) {
      super(radius);
    }
  }

}
