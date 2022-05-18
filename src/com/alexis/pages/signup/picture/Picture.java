package com.alexis.pages.signup.picture;

import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.components._global.Header.*;
import com.alexis.components.signup.picture.Content.*;
import com.alexis.components.signup.picture.Content.ContentProps;

public class Picture extends com.alexis.common.Component.Component {
  private LayoutBuilder layoutBuilder;

  private void initContent() {
    int width = Utils.SCREEN_WIDTH;
    int height = Utils.SCREEN_HEIGHT - 130;
    Point contentPos = layoutBuilder.next(width, height);
    Content content = new Content("Content", this, new ContentProps(new Point((int) contentPos.getX(), (int) contentPos.getY())));
    this.add(content);
  }

  private void initHeader() {
    int width = Utils.SCREEN_WIDTH;
    int height = 130;
    Point headerPos = layoutBuilder.next(width, height);
    Header header = new Header("Header", this,
        new com.alexis.components._global.Header.HeaderProps(new Point((int) headerPos.getX(), (int) headerPos.getY())));
    header.setBounds((int) headerPos.getX(), (int) headerPos.getY(), Utils.SCREEN_WIDTH, 130);
    this.add(header);
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setBackground(Color.WHITE);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setLayout(null);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public void updateProps(ComponentProps props) {};


  public Picture(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.initClassAttributes();
    this.initHeader();
    this.initContent();
  }
}
