package com.alexis.pages.home;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JButton;

import com.alexis.common.Utils;
import com.alexis.common.Component.*;
import com.alexis.common.Components.Components;

public class Home extends Component {
  private JPanel panel;
  private Components components;

  public JPanel getPanel() {
    return this.panel;
  }

  public Home(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.panel = new JPanel();
    this.panel.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.panel.setFocusable(true);
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
    JButton btn = new JButton("Je suis un boutton !");
    this.panel.add(btn);
  }
}
