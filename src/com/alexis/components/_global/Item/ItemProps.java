package com.alexis.components._global.Item;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public abstract class ItemProps extends ComponentProps {
  public final static String TYPE_NAME = "ItemProps";
  public Point position;
  public String icoPath;
  public String title;

  public abstract void onClick();

  public ItemProps(Point pos, String icoPath, String title) {
    super(TYPE_NAME);
    this.position = pos;
    this.icoPath = icoPath;
    this.title = title;
  }
}