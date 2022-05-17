package com.alexis.components._global.Notification;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

import com.alexis.common.Utils;

public class Notification extends com.alexis.common.Component.Component {
  private JLabel message;

  public static void addNotification(JPanel toPanel, String message, Color messageColor, Color notificationColor) {
    Notification notification = new Notification("Notification", null);
    notification.setMessage(message, messageColor, notificationColor);
    toPanel.add(notification);
    toPanel.revalidate();
    toPanel.repaint();

    new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            toPanel.remove(notification);
            toPanel.revalidate();
            toPanel.repaint();
          }
        },
        3000);
  }

  public void setMessage(String message, Color messageColor, Color notificationColor) {
    this.message.setText(message);
    this.message.setForeground(messageColor);
    this.setBackgroundColor(notificationColor);
  }

  public void initClassAttributes() {
    this.setLayout(null);
    this.setOpaque(false);
    this.setBounds(Utils.SCREEN_WIDTH - 500, 0, 500, 70);
    this.message = new JLabel("lol");
    this.message.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.message.setBounds(20, 0, 500, 70);
    this.message.setForeground(Color.BLACK);
    this.add(this.message);
    this.setVisible(true);
  }

  public Notification(String name, com.alexis.common.Component.Component parent) {
    super(name, parent, 30, Color.WHITE);
    this.initClassAttributes();
  }
}
