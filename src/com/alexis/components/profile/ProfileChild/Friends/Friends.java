package com.alexis.components.profile.ProfileChild.Friends;

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
import com.alexis.components.profile.ProfileChild.Friends.FriendsScroll.FriendsScroll;
import com.alexis.components.profile.ProfileChild.Friends.FriendsScroll.FriendsScrollProps;

public class Friends extends com.alexis.common.Component.Component {
  private FriendsProps props;
  private LayoutBuilder layoutBuilder;
  private JScrollPane scrollPane;
  private FriendsScroll friendsScroll;

  private void setStyleScroll() {
    Point pos = this.layoutBuilder.next(800, 370);
    this.scrollPane.setBounds((int)pos.getX(), (int)pos.getY(), 800, 370);
    this.scrollPane.setBorder(null);
    this.friendsScroll.updateContents();
  }

  private void initScroll() {
    this.friendsScroll = new FriendsScroll("FriendsScroll", this, new FriendsScrollProps());
    this.scrollPane = new JScrollPane(this.friendsScroll);
    this.add(scrollPane);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 420);
    this.setBackground(Color.YELLOW);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleScroll();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(FriendsProps.TYPE_NAME)) {
        this.props = (FriendsProps)newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Friends(String name, com.alexis.common.Component.Component parent, FriendsProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initScroll();
    this.setStyleComponents();
  }
}
