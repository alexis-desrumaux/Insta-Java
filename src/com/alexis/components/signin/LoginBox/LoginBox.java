package com.alexis.components.signin.LoginBox;

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
import com.alexis.common.Components.Components;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.RoundedPanel.RoundedPanel;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class LoginBox extends com.alexis.common.Component.Component {
  private JLabel cTitle;
  private Image logo;
  private Point logoPos;
  private JTextField nickNameField;
  private JPasswordField pwdField;
  private JButton signinBtn;
  private JButton newABtn;
  private String username;
  private String password;
  private boolean dontUpdateUsername;
  private boolean dontUpdatePwd;
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void handleOnClickSignin() {
    User user = Store.getInstance().getOtherUsers().findUserByUsername(this.username);

    if (user == null) {
      Notification.addNotification(parent.getParent().getPanel(), "This user doesn't exist", Color.WHITE, Color.RED);
    } else {
      if (user.getPassword().equals(this.password)) {
        Store.getInstance().getApp().displayMainFrame();
        //Notification.addNotification(parent.getParent().getPanel(), "OK", Color.WHITE, Color.GREEN);
      } else {
        Notification.addNotification(parent.getParent().getPanel(), "Incorrect password", Color.WHITE, Color.RED);
      }

    }
  }

  private void activateSigninButton(boolean isActivated) {
    if (isActivated) {
      this.signinBtn.setBackground(Color.BLUE);
      this.signinBtn.setEnabled(true);
    } else {
      this.signinBtn.setBackground(Color.GRAY);
      this.signinBtn.setEnabled(false);
    }
  }

  private void handleOnChangePasswordInput() {

    if (dontUpdatePwd == false) {
      this.password = new String(this.pwdField.getPassword());
      if (this.password.length() != 0 && this.username.length() != 0)
        this.activateSigninButton(true);
      else
        this.activateSigninButton(false);
    }
  }

  private void handleOnChangeNickNameInput() {
    if (dontUpdateUsername == false) {
      this.username = this.nickNameField.getText();
      if (this.password.length() != 0 && this.username.length() != 0)
        this.activateSigninButton(true);
      else
        this.activateSigninButton(false);
    }
  }

  private void initCreateAccountButton() {
    this.newABtn = new JButton("Click here to create an account");
    Point location = layoutBuilder.next(350, 16,
        new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.newABtn.setBounds((int) location.getX(), (int) location.getY(), 350, 16);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, 16).deriveFont(fontAttributes);
    this.newABtn.setFont(plainUnderline);
    this.newABtn.setForeground(Color.BLUE);
    this.newABtn.setOpaque(false);
    this.newABtn.setContentAreaFilled(false);
    this.newABtn.setBorderPainted(false);
    this.newABtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Store.getInstance().getApp().getSigninFrame().changingToSignup();
      }
    });
    this.panel.add(this.newABtn);
  }

  private void initSigninButton() {
    this.signinBtn = new JButton("Sign in");
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.signinBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.signinBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.signinBtn.setBackground(Color.GRAY);
    this.signinBtn.setForeground(Color.WHITE);
    this.signinBtn.setEnabled(false);

    this.signinBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickSignin();
      }
    });

    this.panel.add(this.signinBtn);
  }

  private void initPwdField() {
    this.pwdField = new JPasswordField();
    Point pField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.pwdField.setBounds((int) pField.getX(), (int) pField.getY(), 350, 50);
    this.pwdField.setText("Password");
    this.pwdField.setForeground(new Color(171, 171, 171));
    this.pwdField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.pwdField.setEchoChar((char) 0);
    this.pwdField.setBorder(BorderFactory.createCompoundBorder(
        this.pwdField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.pwdField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdatePwd = false;
        pwdField.setEchoChar('*');
        pwdField.setText(password);
        pwdField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (password.length() == 0) {
          dontUpdatePwd = true;
          pwdField.setEchoChar((char) 0);
          pwdField.setText("Password");
          pwdField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.pwdField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangePasswordInput();
      }
    });
    this.panel.add(this.pwdField);
  }

  private void initNickNameField() {
    this.nickNameField = new JTextField();
    Point nField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.nickNameField.setBounds((int) nField.getX(), (int) nField.getY(), 350, 50);
    this.nickNameField.setText("Username");
    this.nickNameField.setForeground(new Color(171, 171, 171));
    this.nickNameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));

    this.nickNameField.setBorder(BorderFactory.createCompoundBorder(
        this.nickNameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.nickNameField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateUsername = false;
        nickNameField.setText(username);
        nickNameField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (username.length() == 0) {
          dontUpdateUsername = true;
          nickNameField.setText("Username");
          nickNameField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.nickNameField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeNickNameInput();
      }
    });
    this.panel.add(this.nickNameField);
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

  public void initClassAttributes() {
    this.components = new Components(this);
    this.username = "";
    this.password = "";
    this.dontUpdateUsername = false;
    this.dontUpdatePwd = false;
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel = new LoginBoxPanel(30, Color.WHITE);
    this.panel.setLayout(null);
    this.panel.setBounds((Utils.SCREEN_WIDTH / 2) - (700 / 2), (Utils.SCREEN_HEIGHT / 2) - (550 / 2), 700, 550);
    this.panel.setOpaque(false);
    this.panel.setFocusable(true);
  }

  public LoginBox(String name, Components parent) {
    super(name, parent);
    this.initClassAttributes();
    this.initLogo();
    this.initTitle();
    this.initNickNameField();
    this.initPwdField();
    this.initSigninButton();
    this.initCreateAccountButton();
  }

  class LoginBoxPanel extends RoundedPanel {

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
    }

    public LoginBoxPanel(int radius, Color bgColor) {
      super(radius, bgColor);
    }

    public LoginBoxPanel(int radius) {
      super(radius);
    }
  }

}
