package com.alexis.common.SimpleDocumentListener;

import javax.swing.event.*;

public interface SimpleDocumentListener extends DocumentListener {
  void update(DocumentEvent e);

  @Override
  default void insertUpdate(DocumentEvent e) {
      update(e);
  }
  @Override
  default void removeUpdate(DocumentEvent e) {
      update(e);
  }
  @Override
  default void changedUpdate(DocumentEvent e) {
      update(e);
  }
}