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
public class myBatisActionListener implements ActionListener {

    private JTextField urlField;
    private JTextField nameField;
    private JTextField pwdField;
    private JTextField tableField;
    private JTextField showField;

    public myBatisActionListener() {
        super();
    }

    public myBatisActionListener(JTextField urlField, JTextField nameField, JTextField pwdField, JTextField tableField, JTextField showField) {
        this.urlField = urlField;
        this.nameField = nameField;
        this.pwdField = pwdField;
        this.tableField = tableField;
        this.showField = showField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
    }
}
