package com.alexis.pages.home;

import com.alexis.components._global.Layout.LayoutT1.LayoutT1;
import com.alexis.components._global.Layout.LayoutT1.LayoutT1Props;

import java.awt.Color;
import java.awt.Dimension;

import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;

public class Home extends com.alexis.common.Component.Component {
  LayoutT1 layout;

  public void updateProps(ComponentProps props) {};

  public Home(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setFocusable(true);
    this.setLayout(null);
    this.layout = new LayoutT1("LayoutT1", this, new LayoutT1Props(null));
    this.add(layout);
  }
}
