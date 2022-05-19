package com.alexis.components._global.Item;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.Content.Content;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.components._global.Notification.Notification;
import com.alexis.components.profile.ProfileChild.Posts.PostContent.PostContent;
import com.alexis.components.profile.ProfileChild.Posts.PostContent.PostContentProps;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

public class Item extends com.alexis.common.Component.Component {
  private ItemProps props;
  private LayoutBuilder layoutBuilder;

  public ItemProps getProps() {
    return this.props;
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 600, 100);
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
      if (newProps.getPropsTypeName().equals(ItemProps.TYPE_NAME)) {
        this.props = (ItemProps) newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Item(String name, com.alexis.common.Component.Component parent, ItemProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.setStyleComponents();
  }
}
