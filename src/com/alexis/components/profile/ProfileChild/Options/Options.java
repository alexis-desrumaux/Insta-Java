package com.alexis.components.profile.ProfileChild.Options;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
import com.alexis.store.User.USER_TYPE;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.components._global.Notification.Notification;

public class Options extends com.alexis.common.Component.Component {
  private OptionsProps props;
  private LayoutBuilder layoutBuilder;
  private JButton postsBtn;
  private Rectangle postsRec;
  private JButton friendsBtn;
  private Rectangle friendsBtnRec;
  private JButton groupsBtn;
  private Rectangle groupsBtnRec;
  private JButton editBtn;
  private Rectangle editBtnRec;
  private JButton deleteBtn;
  private Rectangle deleteBtnRec;
  private int btnSelected;

  private void setStyleABtn(JButton btn, Rectangle btnRec, int selectedKey) {
    Point pos = layoutBuilder.next(btnRec.width, btnRec.height + 5, new Margin((int)LayoutHelper.getCenter(0, getHeight(), 0, btnRec.height + 5).y, 0, 20 , 0));
    btn.setBounds((int) pos.getX(), (int) pos.getY(), btnRec.width, btnRec.height + 5);
    Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    if (this.btnSelected == selectedKey) {
      Font plainUnderline = new Font("BlinkMacSystemFont", Font.PLAIN, btnRec.height).deriveFont(fontAttributes);
      btn.setFont(plainUnderline);
      btn.setForeground(Color.BLUE);
    } else {
      btn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, btnRec.height));
      btn.setForeground(Color.BLACK);
    }
    Font f = btn.getFont();
    btn.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    btn.setOpaque(false);
    btn.setContentAreaFilled(false);
    btn.setBorderPainted(false);
    btn.setBorder(null);
    btn.setMargin(new Insets(0, 0, 0, 0));
    btn.setVisible(true);
  }

  private void initDeleteBtn() {
    this.deleteBtn = new JButton("Delete");
    this.deleteBtnRec = new Rectangle(0, 0, 0, 14);
    this.deleteBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSelected = 4;
        setStyleComponents();
        props.onClickDelete();
      }
    });
    this.deleteBtn.setVisible(false);
    this.add(this.deleteBtn);
  }

  private void initEditBtn() {
    this.editBtn = new JButton("Edit");
    this.editBtnRec = new Rectangle(0, 0, 0, 14);
    this.editBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSelected = 3;
        setStyleComponents();
        props.onClickEdit();
      }
    });
    this.editBtn.setVisible(false);
    this.add(this.editBtn);
  }

  private void initGroupsBtn() {
    this.groupsBtn = new JButton("Groups");
    this.groupsBtnRec = new Rectangle(0, 0, 0, 14);
    this.groupsBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSelected = 2;
        setStyleComponents();
        props.onClickGroups();
      }
    });
    this.groupsBtn.setVisible(false);
    this.add(this.groupsBtn);
  }

  private void initFriendsBtn() {
    this.friendsBtn = new JButton("Friends");
    this.friendsBtnRec = new Rectangle(0, 0, 0, 14);
    this.friendsBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSelected = 1;
        setStyleComponents();
        props.onClickFriends();
      }
    });
    this.friendsBtn.setVisible(false);
    this.add(this.friendsBtn);
  }

  private void initPostsBtn() {
    this.postsBtn = new JButton("Posts");
    this.postsRec = new Rectangle(0, 0, 0, 14);
    this.postsBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSelected = 0;
        setStyleComponents();
        props.onClickPosts();
      }
    });
    this.postsBtn.setVisible(false);
    this.add(this.postsBtn);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 50);
    this.setBackground(new Color(240, 240, 240));
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.HORIZONTAL_ALIGN);
    this.setStylePanel();
    this.setStyleABtn(this.postsBtn, this.postsRec, 0);
    this.setStyleABtn(this.friendsBtn, this.friendsBtnRec, 1);
    this.setStyleABtn(this.groupsBtn, this.groupsBtnRec, 2);
    this.setStyleABtn(this.editBtn, this.editBtnRec, 3);
    this.setStyleABtn(this.deleteBtn, this.deleteBtnRec, 4);
    this.deleteBtn.setForeground(Color.RED);
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.btnSelected = 0;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    if (this.postsRec.width == 0) {
      this.postsRec.width = g.getFontMetrics(this.postsBtn.getFont()).stringWidth(this.postsBtn.getText());
      this.friendsBtnRec.width = g.getFontMetrics(this.friendsBtn.getFont()).stringWidth(this.friendsBtn.getText());
      this.groupsBtnRec.width = g.getFontMetrics(this.groupsBtn.getFont()).stringWidth(this.groupsBtn.getText());
      this.editBtnRec.width = g.getFontMetrics(this.editBtn.getFont()).stringWidth(this.editBtn.getText());
      this.deleteBtnRec.width = g.getFontMetrics(this.deleteBtn.getFont()).stringWidth(this.deleteBtn.getText());
      this.setStyleComponents();
    }
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(OptionsProps.TYPE_NAME)) {
        this.props = (OptionsProps) props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Options(String name, com.alexis.common.Component.Component parent, OptionsProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initPostsBtn();
    this.initFriendsBtn();
    this.initGroupsBtn();
    this.initEditBtn();
    this.initDeleteBtn();
    this.setStyleComponents();
  }

}
