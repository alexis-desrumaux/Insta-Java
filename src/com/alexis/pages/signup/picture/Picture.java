package com.alexis.pages.signup.picture;

import javax.swing.JPanel;
import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.Components.Components;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.components._global.Header.*;
import com.alexis.components.signup.picture.Content.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Picture extends com.alexis.common.Component.Component {
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void initContent() {
    int width = Utils.SCREEN_WIDTH;
    int height = Utils.SCREEN_HEIGHT - 130;
    Point contentPoint = layoutBuilder.next(width, height);
    this.components.grabComponents().add(new Content("Content", this.components, contentPoint));
  }

  private void initHeader() {
    int width = Utils.SCREEN_WIDTH;
    int height = 130;
    Point headerPoint = layoutBuilder.next(width, height);
    this.components.grabComponents().add(new Header("Header", this.components, headerPoint));
  }

  private void initClassAttributes() {
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.panel.setBackground(Color.WHITE);
    this.panel.setOpaque(false);
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
  }

  public Picture(String name, Components parent) {
    super(name, parent);
    this.initClassAttributes();
    this.initHeader();
    this.initContent();
    for (com.alexis.common.Component.Component c : this.components.grabComponents()) {
      this.panel.add(c.getPanel());
    }
    /*JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
		}*/
  }
}
