package com.alexis.components.profile.ProfileChild.Edit;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class EditProps extends ComponentProps {
  public final static String TYPE_NAME = "EditProps";
  public Point position;

  public EditProps(Point pos) {
    super(TYPE_NAME);
    this.position = pos;
  }
}
