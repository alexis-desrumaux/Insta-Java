package com.alexis.components.profile.ProfileChild.Friends;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class FriendsProps extends ComponentProps {
  public final static String TYPE_NAME = "FriendsProps";
  public Point position;

  public FriendsProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
