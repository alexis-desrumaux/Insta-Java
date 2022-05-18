package com.alexis.components.signup.SignupBox;

import java.awt.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class SignupBoxProps extends ComponentProps {
  public Point position;
  public final static String TYPE_NAME = "SignupBoxProps";

  public SignupBoxProps(Point position) {
    super(TYPE_NAME);
    this.position = position;
  }
}
