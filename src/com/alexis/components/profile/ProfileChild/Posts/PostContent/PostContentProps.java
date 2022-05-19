package com.alexis.components.profile.ProfileChild.Posts.PostContent;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.Content.Content;

public class PostContentProps extends ComponentProps {
  public final static String TYPE_NAME = "PostContentProps";
  public Point position;
  public Content content;

  public PostContentProps(Point pos, Content content) {
    super(TYPE_NAME);
    this.position = pos;
    this.content = content;
  }
}
