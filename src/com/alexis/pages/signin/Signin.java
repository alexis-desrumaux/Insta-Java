package com.alexis.pages.signin;

import java.awt.*;

import com.alexis.components.signin.LoginBox.LoginBox;
import com.alexis.store.Store;
import com.alexis.common.Utils;
import com.alexis.components._global.Notification.*;

public class Signin extends com.alexis.common.Component.Component {
  private LoginBox loginBox;

  @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 50, 700, Color.RED);
      g2D.setPaint(gradient);
      g2D.fillRect(0, 0, getWidth(), getHeight());
    }

  public Signin(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setFocusable(true);
    this.setLayout(null);
    this.loginBox = new LoginBox("LoginBox", this);
    this.add(this.loginBox);
  }
}
