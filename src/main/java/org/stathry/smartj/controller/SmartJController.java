package org.stathry.smartj.controller;

import org.stathry.smartj.constant.ActionCommand;
import org.stathry.smartj.listener.JSONActionListener;
import org.stathry.smartj.model.LabelPosition;

import javax.swing.*;
import java.awt.*;

/**
 * SmartJController
 * Created by dongdaiming on 2018-10-16 10:49
 */
public class SmartJController {

    private static final int LABEL_WIDTH = 80;
    private static final int LABEL_HEIGHT = 25;
    private static final int FIELD_WIDTH = 200;
    private static final int FIELD_HEIGHT = 25;
    private static final int BUTTON_WIDTH = 88;
    private static final int BUTTON_HEIGHT = 25;

    public static JFrame createMainFrame(String frameName, int width, int height) {
        JFrame frame = new JFrame(frameName);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    public static JPanel createJSONHandlePanel(LabelPosition position, String panelTitle) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(panelTitle));
        panel.setLayout(null);
        JLabel srcLabel = new JLabel("input:");
        srcLabel.setBounds(position.getLabelX(), position.getLabelY(), LABEL_WIDTH, LABEL_HEIGHT);
        panel.add(srcLabel);

        JTextField srcField = new JTextField();
        srcField.setBounds(position.getFieldX(), position.getFieldY(), FIELD_WIDTH, FIELD_HEIGHT);
        panel.add(srcField);

        JLabel showLabel = new JLabel("output:");
        showLabel.setBounds(position.getLabelX(), position.nextLabelY(), LABEL_WIDTH, LABEL_HEIGHT);
        panel.add(showLabel);

        JTextField showField = new JTextField();
        showField.setBounds(position.getFieldX(), position.nextFieldY(), FIELD_WIDTH, FIELD_HEIGHT);
        panel.add(showField);

        JButton trimBtn = createButton("trim", ActionCommand.TRIM, position.getLabelX(), position.nextLabelY());
        trimBtn.addActionListener(new JSONActionListener(srcField, showField));
        panel.add(trimBtn);
        JButton completeBtn = createButton("complete", ActionCommand.JSON_COMPLETE, position.nextLabelX(), position.getLabelY());
        completeBtn.addActionListener(new JSONActionListener(srcField, showField));
        panel.add(completeBtn);
        JButton escapeBtn = createButton("escape", ActionCommand.ESCAPE_JAVA, position.nextLabelX(), position.getLabelY());
        escapeBtn.addActionListener(new JSONActionListener(srcField, showField));
        panel.add(escapeBtn);

        return panel;
    }

    public static JButton createButton(String buttonText, String command, int x, int y) {
        JButton btn = new JButton(buttonText);
        btn.setActionCommand(command);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        return btn;
    }
}
