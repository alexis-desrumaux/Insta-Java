package com.alexis.common.LayoutBuilder;

import java.awt.Point;

import javax.swing.text.Position;

public class LayoutBuilder {
  private Point pos;
  private int align;

  public static final int VERTICAL_ALIGN = 0;
  public static final int HORIZONTAL_ALIGN = 1;

  public void setPosition(Point p) {
    this.pos = p;
  }

  public void changeAlign(int ALIGN) {
    this.align = ALIGN;
  }

  public Point next(int width, int height, Margin margin) {
    Point p = new Point((int)this.pos.getX() + margin.marginLeft - margin.marginRight, (int)this.pos.getY() + margin.marginTop - margin.marginBottom);

    if (this.align == VERTICAL_ALIGN) {
      this.pos.setLocation(this.pos.getX(), this.pos.getY() + height + margin.marginTop - margin.marginBottom);
    } else {
      this.pos.setLocation(this.pos.getX() + width + margin.marginLeft - margin.marginRight, this.pos.getY());
    }
    return p;
  }

  public Point next(int width, int height) {
    return this.next(width, height, new Margin(0, 0, 0, 0));
  }

  public LayoutBuilder(int posX, int posY, int ALIGN) {
    this.pos = new Point(posX, posY);
    this.align = ALIGN;
  }
}
