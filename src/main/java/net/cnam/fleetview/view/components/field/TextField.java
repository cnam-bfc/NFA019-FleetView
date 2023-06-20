package net.cnam.fleetview.view.components.field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField implements FocusListener {
    private String placeholder;

    private boolean placeholderVisible = true;

    public TextField() {
        super();

        // Focus listener
        this.addFocusListener(this);
    }

    private void setPlaceholderVisible(boolean visible) {
        if (visible) {
            super.setText(this.placeholder);
            this.setForeground(Color.GRAY);
        } else {
            super.setText("");
            this.setForeground(Color.BLACK);
        }
        this.placeholderVisible = visible;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (placeholderVisible) {
            setPlaceholderVisible(false);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            setPlaceholderVisible(true);
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        if (placeholderVisible || this.getText().isEmpty()) {
            setPlaceholderVisible(true);
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public String getText() {
        if (placeholderVisible) {
            return "";
        } else {
            return super.getText();
        }
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (text.isEmpty()) {
            setPlaceholderVisible(true);
        } else if (placeholderVisible) {
            setPlaceholderVisible(false);
        }
    }
}
