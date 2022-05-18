package com.alexis.components._global.Layout.LayoutT1.LeftNavbar.Links;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class LinksProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "LinksProps";

  public LinksProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}
