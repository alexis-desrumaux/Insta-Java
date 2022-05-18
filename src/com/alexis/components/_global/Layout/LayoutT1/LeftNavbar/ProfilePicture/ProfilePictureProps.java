package com.alexis.components._global.Layout.LayoutT1.LeftNavbar.ProfilePicture;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class ProfilePictureProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "ProfilePictureProps";

  public ProfilePictureProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}
