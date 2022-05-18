package com.alexis.components.signup.informations.Content;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class ContentProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "ContentProps";

  public ContentProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}