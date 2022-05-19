package com.alexis.components.profile.ProfileChild;

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
import com.alexis.components.profile.ProfileChild.Edit.Edit;
import com.alexis.components.profile.ProfileChild.Edit.EditProps;
import com.alexis.components.profile.ProfileChild.Friends.Friends;
import com.alexis.components.profile.ProfileChild.Friends.FriendsProps;
import com.alexis.components.profile.ProfileChild.Groups.Groups;
import com.alexis.components.profile.ProfileChild.Groups.GroupsProps;
import com.alexis.components.profile.ProfileChild.HeaderBackground.HeaderBackground;
import com.alexis.components.profile.ProfileChild.HeaderBackground.HeaderBackgroundProps;
import com.alexis.components.profile.ProfileChild.Options.Options;
import com.alexis.components.profile.ProfileChild.Options.OptionsProps;
import com.alexis.components.profile.ProfileChild.Posts.Posts;
import com.alexis.components.profile.ProfileChild.Posts.PostsProps;

public class ProfileChild extends com.alexis.common.Component.Component  {
  private ProfileChildProps props;
  private LayoutBuilder layoutBuilder;
  private HeaderBackground headerBackground;
  private Posts postsPanel;
  private Friends friendsPanel;
  private Groups groupsPanel;
  private Edit editPanel;
  private Options options;
  private com.alexis.common.Component.Component content;
  private String currentContentName;
  
  private void handleOnClickEdit() {
    if (this.currentContentName.equals("Edit"))
      return;
    if (this.content != null) {
      this.remove(this.content);
    }
    Point pos = layoutBuilder.next(800, 420);
    this.editPanel = new Edit("Edit", this, new EditProps(pos));
    this.initContent(this.editPanel);
    this.revalidate();
    this.setStyleComponents();
  }

  private void handleOnClickGroups() {
    if (this.currentContentName.equals("Groups"))
      return;
    if (this.content != null) {
      this.remove(this.content);
    }
    Point pos = layoutBuilder.next(800, 420);
    this.groupsPanel = new Groups("Groups", this, new GroupsProps(pos));
    this.initContent(this.groupsPanel);
    this.revalidate();
    this.setStyleComponents();
  }

  private void handleOnClickFriends() {
    if (this.currentContentName.equals("Friends"))
      return;
    if (this.content != null) {
      this.remove(this.content);
    }
    Point pos = layoutBuilder.next(800, 420);
    this.friendsPanel = new Friends("Friends", this, new FriendsProps(pos));
    this.initContent(this.friendsPanel);
    this.revalidate();
    this.setStyleComponents();
  }

  private void handleOnClickPosts() {
    if (this.currentContentName.equals("Posts"))
      return;
    if (this.content != null) {
      this.remove(this.content);
    }
    Point pos = layoutBuilder.next(800, 420);
    this.postsPanel = new Posts("Posts", this, new PostsProps(pos));
    this.initContent(this.postsPanel);
    this.revalidate();
    this.setStyleComponents();
  }

  private void setStyleContent() {
    Point pos = layoutBuilder.next(800, 420);

    if (this.currentContentName.equals("Posts")) {
      this.content.updateProps(new PostsProps(pos));
    } else if (this.currentContentName.equals("Friends")) {
      this.content.updateProps(new FriendsProps(pos));
    } else if (this.currentContentName.equals("Groups")) {
      this.content.updateProps(new GroupsProps(pos));
    } else if (this.currentContentName.equals("Edit")) {
      this.content.updateProps(new EditProps(pos));
    }
    this.content.setVisible(true);
  }
  
  private void initContent(com.alexis.common.Component.Component component) {
    this.content = component;
    this.currentContentName = this.content.getName();
    this.content.setVisible(false);
    this.add(this.content);
  }

  private void setStyleOptions() {
    Point pos = layoutBuilder.next(800, 50);
    this.options.updateProps(new OptionsProps(pos) {
      @Override
      public void onClickPosts() {
        System.out.println("Click post button");
        handleOnClickPosts();
      }
      @Override
      public void onClickFriends() {
        System.out.println("Click friends button");
        handleOnClickFriends();
      }
      @Override
      public void onClickGroups() {
        System.out.println("Click groups button");
        handleOnClickGroups();
      }
      @Override
      public void onClickEdit() {
        System.out.println("Click edit button");
        handleOnClickEdit();
      }
      @Override
      public void onClickDelete() {
        System.out.println("Click delete button");
      }
    });
    this.options.setVisible(true);
  }

  private void initOptions() {
    Point pos = layoutBuilder.next(800, 50);
    this.options = new Options("Options", this, new OptionsProps(pos) {
      @Override
      public void onClickPosts() {
        System.out.println("Click post button");
        handleOnClickPosts();
      }
      @Override
      public void onClickFriends() {
        System.out.println("Click friends button");
        handleOnClickFriends();
      }
      @Override
      public void onClickGroups() {
        System.out.println("Click groups button");
        handleOnClickGroups();
      }
      @Override
      public void onClickEdit() {
        System.out.println("Click edit button");
        handleOnClickEdit();
      }
      @Override
      public void onClickDelete() {
        System.out.println("Click delete button");
      }
    });
    this.options.setVisible(false);
    this.add(options);
  }

  private void setStyleHeaderBackground() {
    Point pos = layoutBuilder.next(800, 230);
    this.headerBackground.updateProps(new HeaderBackgroundProps(pos));
    this.headerBackground.setVisible(true);
  }

  private void initHeaderBackground() {
    Point pos = layoutBuilder.next(800, 230);
    this.headerBackground = new HeaderBackground("HeaderBackground", this, new HeaderBackgroundProps(pos));
    this.headerBackground.setVisible(false);
    this.add(headerBackground);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 700);
    this.setBackground(Color.WHITE);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleHeaderBackground();
    this.setStyleOptions();
    this.setStyleContent();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.currentContentName = "";
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(ProfileChildProps.TYPE_NAME)) {
        this.props = (ProfileChildProps)newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ProfileChild(String name, com.alexis.common.Component.Component parent, ProfileChildProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initHeaderBackground();
    this.initOptions();
    this.handleOnClickPosts();
    this.setStyleComponents();
  }
}
