package com.alexis.components.profile.ProfileChild.Posts.PostsScroll;

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

public class PostsScroll extends com.alexis.common.Component.Component {
  private PostsScrollProps props;
  private LayoutBuilder layoutBuilder;
  private ArrayList<PostContent> posts;

  public void updateContents() {
    this.removeAll();
    this.posts.clear();
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.initPosts();
    this.setStyleComponents();
    this.revalidate();
  }
  
  private void setStylePosts() {
    for (PostContent c : this.posts) {
      Point pos = layoutBuilder.next(700, 200, new Margin(20, 0, LayoutHelper.getCenter(780, 0, 700, 0).x, 0));
      c.updateProps(new PostContentProps(pos, c.getProps().content));
    }
  }

  private void initPosts() {
    ArrayList<Content> contents = Store.getInstance().getUser().getContents();
    ArrayList<Content> contentsOrdered = new ArrayList<Content>();
    ArrayList<Long> orderedTimestamp = new ArrayList<Long>();

    for (Content c : contents) {
      orderedTimestamp.add(c.getCreationTime());
    }
    Collections.sort(orderedTimestamp);
    for (long t : orderedTimestamp) {
      for (Content c : contents) {
        if (c.getCreationTime() == t) {
          contentsOrdered.add(c);
        }
      }
    }
    Collections.reverse(contentsOrdered);
    int i = 0;

    for (Content c : contentsOrdered) {
      Point pos = layoutBuilder.next(700, 200, new Margin(20, 0, LayoutHelper.getCenter(780, 0, 700, 0).x, 0));
      PostContent p = new PostContent("PostContent" + i, this, new PostContentProps(pos, c));
      this.posts.add(p);
      this.add(p);
      i += 1;
    }
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(0, 0, 780, 0);
    this.setPreferredSize(new Dimension(780, 0));
    this.setBackground(new Color(241, 243, 245));
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStylePosts();
    this.setBounds(0, 0, 780, this.layoutBuilder.next(0, 0).y);
    this.setPreferredSize(new Dimension(780, this.layoutBuilder.next(0, 0).y));
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.posts = new ArrayList<PostContent>();
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
    this.initPosts();
    this.setStyleComponents();
  }
}
