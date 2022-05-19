package com.alexis.components.profile.ProfileChild.HeaderBackground;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class HeaderBackgroundProps extends ComponentProps {
  public final static String TYPE_NAME = "HeaderBackgroundProps";
  public Point position;

  public HeaderBackgroundProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
