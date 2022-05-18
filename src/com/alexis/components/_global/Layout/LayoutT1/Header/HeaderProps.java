package com.alexis.components._global.Layout.LayoutT1.Header;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;


public class HeaderProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "HeaderProps";

  public HeaderProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}
