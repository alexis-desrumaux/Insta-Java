package com.alexis.components.profile.ProfileChild.Edit;

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

public class Edit extends com.alexis.common.Component.Component {
  private EditProps props;
  private LayoutBuilder layoutBuilder;

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 420);
    this.setBackground(Color.MAGENTA);
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
      if (newProps.getPropsTypeName().equals(EditProps.TYPE_NAME)) {
        this.props = (EditProps)newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Edit(String name, com.alexis.common.Component.Component parent, EditProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.setStyleComponents();
  }
}
