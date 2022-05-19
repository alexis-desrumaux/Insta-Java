package com.alexis.components.profile.ProfileChild;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class ProfileChildProps extends ComponentProps {
  
  public final static String TYPE_NAME = "ProfileProps";
  public Point position;

  public ProfileChildProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
