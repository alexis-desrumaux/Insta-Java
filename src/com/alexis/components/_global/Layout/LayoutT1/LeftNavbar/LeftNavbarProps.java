package com.alexis.components._global.Layout.LayoutT1.LeftNavbar;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class LeftNavbarProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "LeftNavbarProps";

  public LeftNavbarProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}
