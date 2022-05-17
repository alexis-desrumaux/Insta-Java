package com.alexis.components.signup.SignupBox;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.common.UserType.StandardUser.StandardUser;
import com.alexis.components._global.Notification.Notification;
import com.alexis.components.signup.SignupBox.Props;
import com.alexis.db.InitDB;
import com.alexis.store.User;

public class SignupBox extends com.alexis.common.Component.Component {
  private JLabel cTitle;
  private Image logo;
  private Point logoPos;
  private JTextField emailField;
  private JTextField usernameField;
  private JPasswordField pwdField;
  private JButton signupBtn;
  private JButton signinBtn;
  private String email;
  private String username;
  private String password;
  private boolean dontUpdateEmail;
  private boolean dontUpdateUsername;
  private boolean dontUpdatePwd;
  private LayoutBuilder layoutBuilder;

  private void handleOnClickSignup() {
    User user = Store.getInstance().getOtherUsers().findUserByUsername(this.username);
    if (user != null) {
      Notification.addNotification(this.parent, "User " + this.username + " already exist", Color.BLACK, Color.YELLOW);
      return;
    }
    ArrayList<String> hobbies = new ArrayList<String>();
    hobbies.add("NONE");
    User newUser = new StandardUser(this.username, this.password, "NONE", "NONE", 0, this.email, hobbies, "NONE");
    Store.getInstance().setUser(newUser);
    UserSaveFileParser saveParser = new UserSaveFileParser(Utils.getSaveFilePathByUsername(this.username));
    saveParser.save(newUser);
    InitDB.addUserToUsernameList(this.username);
    Store.getInstance().getApp().getSigninFrame().changingToInformations();
  }

  private void activateSignupButton(boolean isActivated) {
    if (isActivated) {
      this.signupBtn.setBackground(Color.BLUE);
      this.signupBtn.setEnabled(true);
    } else {
      this.signupBtn.setBackground(Color.GRAY);
      this.signupBtn.setEnabled(false);
    }
  }

  private void handleOnChangePasswordInput() {

    if (dontUpdatePwd == false) {
      this.password = new String(this.pwdField.getPassword());
      if (this.password.length() != 0 && this.email.length() != 0 && this.username.length() != 0)
        this.activateSignupButton(true);
      else
        this.activateSignupButton(false);
    }
  }

  private void handleOnChangeUsernameInput() {
    if (dontUpdateUsername == false) {
      this.username = this.usernameField.getText();
      if (this.password.length() != 0 && this.email.length() != 0 && this.username.length() != 0)
        this.activateSignupButton(true);
      else
        this.activateSignupButton(false);
    }
  }

  private void handleOnChangeEmailInput() {
    if (dontUpdateEmail == false) {
      this.email = this.emailField.getText();
      if (this.password.length() != 0 && this.email.length() != 0 && this.username.length() != 0)
        this.activateSignupButton(true);
      else
        this.activateSignupButton(false);
    }
  }

  private void initSigninButton() {
    this.signinBtn = new JButton("Click here to log in");
    Point location = layoutBuilder.next(350, 16,
        new Margin(20, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
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
    this.add(this.signinBtn);
  }

  private void initSignupButton() {
    this.signupBtn = new JButton("Sign up");
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.signupBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.signupBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.signupBtn.setBackground(Color.GRAY);
    this.signupBtn.setForeground(Color.WHITE);
    this.signupBtn.setEnabled(false);

    this.signupBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickSignup();
      }
    });

    this.add(this.signupBtn);
  }

  private void initPwdField() {
    this.pwdField = new JPasswordField();
    Point pField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.pwdField.setBounds((int) pField.getX(), (int) pField.getY(), 350, 50);
    this.pwdField.setText("Password");
    this.pwdField.setForeground(new Color(171, 171, 171));
    this.pwdField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
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
    this.add(this.pwdField);
  }

  private void initUsernameField() {
    this.usernameField = new JTextField();
    Point uField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.usernameField.setBounds((int) uField.getX(), (int) uField.getY(), 350, 50);
    this.usernameField.setText("Username");
    this.usernameField.setForeground(new Color(171, 171, 171));
    this.usernameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.usernameField.setBorder(BorderFactory.createCompoundBorder(
        this.usernameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.usernameField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateUsername = false;
        usernameField.setText(username);
        usernameField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (username.length() == 0) {
          dontUpdateUsername = true;
          usernameField.setText("Username");
          usernameField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.usernameField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeUsernameInput();
      }
    });
    this.add(this.usernameField);

  }

  private void initEmailField() {
    this.emailField = new JTextField();
    Point eField = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.emailField.setBounds((int) eField.getX(), (int) eField.getY(), 350, 50);
    this.emailField.setText("E-mail");
    this.emailField.setForeground(new Color(171, 171, 171));
    this.emailField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.emailField.setBorder(BorderFactory.createCompoundBorder(
        this.emailField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.emailField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateEmail = false;
        emailField.setText(email);
        emailField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (email.length() == 0) {
          dontUpdateEmail = true;
          emailField.setText("E-mail");
          emailField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.emailField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeEmailInput();
      }
    });

    this.add(this.emailField);
  }

  private void initLogo() {
    try {
      ImageIcon ii = new ImageIcon("src/com/alexis/assets/logo.png");
      this.logo = ii.getImage();
      this.logo = this.logo.getScaledInstance(341, 100, Image.SCALE_SMOOTH);
      this.logoPos = layoutBuilder.next(341, 100,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 341, 0).getX(), 0));

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
        new Margin(20, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 320, 0).getX(), 0));
    this.cTitle.setBounds((int) cTitlePos.getX(), (int) cTitlePos.getY(), 320, 30);
    this.add(this.cTitle);
  }

  private void initClassAttributes(Props props) {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds((int)props.position.getX(), (int)props.position.getY(), 700, 600);
    this.email = "";
    this.username = "";
    this.password = "";
    this.dontUpdateEmail = false;
    this.dontUpdateUsername = false;
    this.dontUpdatePwd = false;
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(logo, (int) logoPos.getX(), (int) logoPos.getY(), this);
  }

  public SignupBox(String name, com.alexis.common.Component.Component parent, Props props) {
    super(name, parent, 30, Color.WHITE);
    this.initClassAttributes(props);
    this.initLogo();
    this.initTitle();
    this.initEmailField();
    this.initUsernameField();
    this.initPwdField();
    this.initSignupButton();
    this.initSigninButton();
  }
}
