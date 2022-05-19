package com.alexis.pages.profile;

import com.alexis.components._global.Layout.LayoutT1.LayoutT1;
import com.alexis.components._global.Layout.LayoutT1.LayoutT1Props;
import com.alexis.components.profile.ProfileChild.*;

import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;

public class Profile extends com.alexis.common.Component.Component {
  public LayoutT1 layout;
  public ProfileChild p;

  public void updateProps(ComponentProps props) {};

  public Profile(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setFocusable(true);
    this.setLayout(null);
    this.p = new ProfileChild("ProfileChild", this, new ProfileChildProps(new Point(0, 0)));
    this.layout = new LayoutT1("LayoutT1", this, new LayoutT1Props(this.p));
    this.p.updateProps(new ProfileChildProps(this.layout.getPositionChild()));
    this.add(layout);
  }
}
