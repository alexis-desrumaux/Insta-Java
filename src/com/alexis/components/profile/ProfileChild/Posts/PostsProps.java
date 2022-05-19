package com.alexis.components.profile.ProfileChild.Posts;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class PostsProps extends ComponentProps {
  public final static String TYPE_NAME = "PostsProps";
  public Point position;

  public PostsProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
