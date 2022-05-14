package com.alexis.common.LayoutHelper;

import java.awt.Point;

public class LayoutHelper {
  public static Point getCenter(int parentWidth, int parentHeight, int childWidth, int childHeight) {
    return new Point((parentWidth / 2) - (childWidth / 2), (parentHeight / 2) - (childHeight / 2));
  }
}
