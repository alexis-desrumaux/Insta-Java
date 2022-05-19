package com.alexis.components.profile.ProfileChild.Friends.FriendsScroll;

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
import com.alexis.components._global.Item.Item;
import com.alexis.components._global.Item.ItemProps;
import com.alexis.components._global.Notification.Notification;
import com.alexis.components.profile.ProfileChild.Posts.PostContent.PostContent;
import com.alexis.components.profile.ProfileChild.Posts.PostContent.PostContentProps;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

public class FriendsScroll extends com.alexis.common.Component.Component {
  private FriendsScrollProps props;
  private LayoutBuilder layoutBuilder;
  private Map<User, Item> friends;

  public void updateContents() {
    this.removeAll();
    this.friends.clear();
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.initFriends();
    this.setStyleComponents();
    this.revalidate();
  }
  
  private void setStylePosts() {
    for (Map.Entry<User, Item> entry : this.friends.entrySet()) {
      Point pos = layoutBuilder.next(600, 100, new Margin(20, 0, LayoutHelper.getCenter(780, 0, 600, 0).x, 0));
      entry.getValue().updateProps(new ItemProps(pos, entry.getValue().getProps().icoPath, entry.getValue().getProps().title) {
        @Override
        public void onClick() {
          System.out.println("Click on " + entry.getKey().getNickName());
        }
      });
    }
  }

  private void initFriends() {
    System.out.println("TEST!!!!!!!!!!");
    ArrayList<User> friends = Store.getInstance().getUser().getFollows();
    int i = 0;
    for (User u : friends) {
      System.out.println(u.getNickName());
      Point pos = layoutBuilder.next(700, 200, new Margin(20, 0, LayoutHelper.getCenter(780, 0, 700, 0).x, 0));
      Item p = new Item("Item" + i, this, new ItemProps(pos, u.getPPPath(), u.getNickName()) {
        @Override
        public void onClick() {

        }
      });
      this.friends.put(u, p);
      this.add(p);
      i += 1;
    }
    
    /*ArrayList<Content> contents = Store.getInstance().getUser().getContents();
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
      System.out.println(c.getTitle());
      Point pos = layoutBuilder.next(700, 200, new Margin(20, 0, LayoutHelper.getCenter(780, 0, 700, 0).x, 0));
      PostContent p = new PostContent("PostContent" + i, this, new PostContentProps(pos, c));
      this.posts.add(p);
      this.add(p);
      i += 1;
    }*/
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
    this.friends = new HashMap<User, Item>();
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(FriendsScrollProps.TYPE_NAME)) {
        this.props = (FriendsScrollProps) newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public FriendsScroll(String name, com.alexis.common.Component.Component parent, FriendsScrollProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initFriends();
    this.setStyleComponents();
  }
}
