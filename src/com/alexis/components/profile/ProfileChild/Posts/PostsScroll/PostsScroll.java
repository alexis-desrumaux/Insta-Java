package com.alexis.components.profile.ProfileChild.Posts.PostsScroll;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.ArrayList;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

public class PostsScroll extends com.alexis.common.Component.Component {
  private PostsScrollProps props;
  private LayoutBuilder layoutBuilder;
  private JButton b;

  private void setStyleB() {
    Point pos = this.layoutBuilder.next(780, 500);
    this.b.setBounds((int)pos.getX(), (int)pos.getY(), 780, 500);
  }

  private void initB() {
    this.b = new JButton("LOUL");
    this.add(b);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(0, 0, 780, 0);
    this.setPreferredSize(new Dimension(780, 0));
    this.setBackground(Color.YELLOW);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleB();
    this.setBounds(0, 0, 780, this.layoutBuilder.next(0, 0).y);
    this.setPreferredSize(new Dimension(780, this.layoutBuilder.next(0, 0).y));
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(PostsScrollProps.TYPE_NAME)) {
        this.props = (PostsScrollProps) newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public PostsScroll(String name, com.alexis.common.Component.Component parent, PostsScrollProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initB();
    this.setStyleComponents();
  }
}
