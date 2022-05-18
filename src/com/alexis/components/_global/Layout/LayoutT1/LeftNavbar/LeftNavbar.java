package com.alexis.components._global.Layout.LayoutT1.LeftNavbar;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;

import java.awt.Color;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.Links.Links;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.Links.LinksProps;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.ProfilePicture.ProfilePicture;
import com.alexis.components._global.Layout.LayoutT1.LeftNavbar.ProfilePicture.ProfilePictureProps;
import com.alexis.components._global.Notification.Notification;

public class LeftNavbar extends com.alexis.common.Component.Component {
  private LeftNavbarProps props;
  private ProfilePicture profilePicture;
  private Links links;
  private LayoutBuilder layoutBuilder;

  private void setStyleLinks() {
    Point pos = layoutBuilder.next(270, 100);
    this.links.updateProps(new LinksProps(pos));
    this.links.setVisible(true);
  }

  private void initLinks() {
    Point pos = layoutBuilder.next(270, 100);
    this.links = new Links("Links", this, new LinksProps(pos));
    this.links.setVisible(false);
    this.add(this.links);
  }

  private void setStyleProfilePicture() {
    Point pos = layoutBuilder.next(270, 230);
    this.profilePicture.updateProps(new ProfilePictureProps(pos));
    this.profilePicture.setVisible(true);
  }

  private void initProfilePicture() {
    Point pos = layoutBuilder.next(270, 230);
    this.profilePicture = new ProfilePicture("ProfilePicture", this, new ProfilePictureProps(pos));
    this.profilePicture.setVisible(false);
    this.add(this.profilePicture);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(false);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 270, 500);
    //this.setPreferredSize(new Dimension(300, 900));
    this.setForeground(Color.WHITE);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePanel();
    this.setStyleProfilePicture();
    this.setStyleLinks();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(LeftNavbarProps.TYPE_NAME)) {
        this.props = (LeftNavbarProps) props;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public LeftNavbar(String name, com.alexis.common.Component.Component parent, LeftNavbarProps props) {
    super(name, parent, 0, Color.WHITE);
    this.props = props;
    this.initClassAttributes();
    this.initProfilePicture();
    this.initLinks();
    this.setStyleComponents();
  }
}
