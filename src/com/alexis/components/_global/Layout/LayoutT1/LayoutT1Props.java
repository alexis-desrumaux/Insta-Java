package com.alexis.components._global.Layout.LayoutT1;

import com.alexis.common.Component.*;
import com.alexis.common.ComponentProps.ComponentProps;

public class LayoutT1Props extends ComponentProps {
  public Component child;
  public final static String TYPE_NAME = "LayoutT1Props";

  public LayoutT1Props(Component child) {
    super(TYPE_NAME);
    this.child = child;
  }
}
