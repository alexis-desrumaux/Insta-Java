package com.alexis.components.profile.ProfileChild.Groups;

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

public class Groups extends com.alexis.common.Component.Component {
  private GroupsProps props;
  private LayoutBuilder layoutBuilder;

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 420);
    this.setBackground(Color.BLUE);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(GroupsProps.TYPE_NAME)) {
        this.props = (GroupsProps)newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Groups(String name, com.alexis.common.Component.Component parent, GroupsProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.setStyleComponents();
  }
}
