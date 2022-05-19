package com.alexis.components.profile.ProfileChild.Options;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public abstract class OptionsProps extends ComponentProps {
  public final static String TYPE_NAME = "OptionsProps";
  public Point position;

  public abstract void onClickPosts();
  public abstract void onClickFriends();
  public abstract void onClickGroups();
  public abstract void onClickEdit();
  public abstract void onClickDelete();

  public OptionsProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}