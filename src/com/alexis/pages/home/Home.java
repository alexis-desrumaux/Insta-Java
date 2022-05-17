package com.alexis.pages.home;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JButton;

import com.alexis.common.Utils;

public class Home extends com.alexis.common.Component.Component {
  public Home(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setFocusable(true);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JButton btn = new JButton("Je suis un boutton !");
    this.add(btn);
  }
}
