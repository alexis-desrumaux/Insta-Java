package com.alexis.pages.signin;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.alexis.common.component.*;
import com.alexis.common.components.Components;
import com.alexis.store.Store;
import com.alexis.common.Utils;
import com.alexis.frames.*;

public class Signin extends Component {
  private JPanel panel;
  private Components components;
  private boolean isLogged;

  public JPanel getPanel() {
    return this.panel;
  }

  public Signin(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.isLogged = false;
    this.panel = new JPanel();
    this.panel.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.panel.setFocusable(true);
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
    JButton btn = new JButton("Je suis un boutton !");
    this.panel.add(btn);
    JButton btn2 = new JButton("Je suis un deuxième boutton !");
    btn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Store.getInstance().getApp().displayMainFrame();
      }
    });
    this.panel.add(btn2);
  }
}
