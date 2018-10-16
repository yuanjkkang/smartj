package org.stathry.smartj.listener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.stathry.smartj.commons.utils.SmartJsonUtils;
import org.stathry.smartj.constant.ActionCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JSONActionListener
 * Created by dongdaiming on 2018-10-16 11:00
 */
public class JSONActionListener implements ActionListener {

    private JTextField srcField;
    private JTextField showField;

    public JSONActionListener() {
        super();
    }
    public JSONActionListener(JTextField srcField, JTextField showField) {
        super();
        this.srcField = srcField;
        this.showField = showField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String in = srcField.getText();
        String value;
        switch (cmd) {
            case ActionCommand.TRIM:
                value = in.trim().replaceAll("\\s", "");
                break;
            case ActionCommand.JSON_COMPLETE:
                value = SmartJsonUtils.completeJSON(in);
                break;
            case ActionCommand.ESCAPE_JAVA:
                value = StringEscapeUtils.escapeJava(in);
                break;
            default:
                System.out.println("not support cmd " + cmd);
                value = "";
        }
        showField.setText(value);
    }
}
