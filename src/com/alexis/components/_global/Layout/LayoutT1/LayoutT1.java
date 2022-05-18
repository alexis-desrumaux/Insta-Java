package com.alexis.components._global.Layout.LayoutT1;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;


import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;
import com.alexis.components._global.Layout.LayoutT1.Header.Header;
import com.alexis.components._global.Layout.LayoutT1.Header.HeaderProps;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.LeftNavbar;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.LeftNavbarProps;

public class LayoutT1 extends com.alexis.common.Component.Component {
  private LayoutT1Props props;
  private Header header;
  private LeftNavbar leftNavbar;
  private JScrollPane leftNavbarScroller;
  private LayoutBuilder layoutBuilder;
  private Image bg;


  private void setStyleLeftNavbar() {
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point leftNavbarPos = layoutBuilder.next(270, 500, new Margin(30, 0, 50, 0));

    this.leftNavbar.updateProps(new LeftNavbarProps(leftNavbarPos));


    /*Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point leftNavbarPos = layoutBuilder.next(300, 500/* , new Margin(30, 0, 20, 0) );
    this.leftNavbarScroller.setBounds((int) leftNavbarPos.getX(), (int) leftNavbarPos.getY(), 300, 500);
    /*
     * Point leftNavbarPos = layoutBuilder.next(300, 900, new Margin(30, 0, 20, 0));
     * this.leftNavbar.updateProps(new LeftNavbarProps(leftNavbarPos));
     */
  }

  private void initLeftNavbar() {
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point leftNavbarPos = layoutBuilder.next(350, 500, new Margin(30, 0, 20, 0));
    this.leftNavbar = new LeftNavbar("LeftNavbar", this, new LeftNavbarProps(leftNavbarPos));
    this.add(leftNavbar);



    /*Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point leftNavbarPos = layoutBuilder.next(350, 500/* , new Margin(30, 0, 20, 0) );

    this.leftNavbar = new LeftNavbar("LeftNavbar", this, new LeftNavbarProps(new Point(0, 0)));
    this.leftNavbarScroller = new JScrollPane(this.leftNavbar);
    this.leftNavbarScroller.setBackground(Color.GREEN);
    this.leftNavbarScroller.setBounds((int) leftNavbarPos.getX(), (int) leftNavbarPos.getY(), 350, 500);
    this.add(this.leftNavbarScroller);*/
  }

  private void setStyleHeader() {
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point headerPos = layoutBuilder.next(Utils.SCREEN_WIDTH, 60);
    this.header.updateProps(new HeaderProps(headerPos));
  }

  private void initHeader() {
    Point headerPos = layoutBuilder.next(Utils.SCREEN_WIDTH, 60);
    this.header = new Header("Header", this, new HeaderProps(headerPos));
    this.add(header);
  }

  private void initBackground() {
    try {
      ImageIcon ii = new ImageIcon("src" + File.separator + "com" + File.separator + "alexis" + File.separator + "assets" + File.separator + "graybgcolor.jpg");
      this.bg = ii.getImage();
      this.bg = bg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStyleHeader();
    this.setStyleLeftNavbar();
  }

  public void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.setLayout(null);
    this.setBackgroundColor(null/*new Color(241, 243, 245)*/);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setVisible(true);
    this.setBounds(0, 0, Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(bg, 0, 0, this);
    
  }

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(LayoutT1Props.TYPE_NAME)) {
        this.props = (LayoutT1Props) props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public LayoutT1(String name, com.alexis.common.Component.Component parent, LayoutT1Props props) {
    super(name, parent);
    this.initClassAttributes();
    this.initBackground();
    this.initHeader();
    this.initLeftNavbar();
    this.setStyleComponents();
  }
}
