package com.alexis.components.profile.ProfileChild.Groups;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class GroupsProps extends ComponentProps {
  public final static String TYPE_NAME = "GroupsProps";
  public Point position;

  public GroupsProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
