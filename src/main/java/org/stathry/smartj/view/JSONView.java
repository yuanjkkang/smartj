package org.stathry.smartj.view;

import org.stathry.smartj.constant.ActionCommand;
import org.stathry.smartj.listener.JSONActionListener;

import javax.swing.*;

/**
 * JSONView
 * Created by dongdaiming on 2018-10-16 15:48
 */
public class JSONView {

    public static JPanel createPanel(String panelTitle) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(panelTitle));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel srcLabel = new JLabel("输入:");
        panel.add(srcLabel);

        JTextField srcField = new JTextField();
        panel.add(srcField);

        JTextArea showField = new JTextArea();
        createButtons(panel, srcField, showField);

        JLabel showLabel = new JLabel("输出:");
        panel.add(showLabel);

        panel.add(showField);
        return panel;
    }

    private static void createButtons(JPanel panel, JTextField srcField, JTextArea showField) {
        JPanel opPanel = new JPanel();
        panel.add(opPanel);

        JButton beautyBtn =
                SmartJView.createButton("美化", ActionCommand.JSON_BEAUTY);
        beautyBtn.addActionListener(new JSONActionListener(srcField, showField));
        opPanel.add(beautyBtn);

        JButton trimBtn = SmartJView.createButton("精简", ActionCommand.TRIM);
        trimBtn.addActionListener(new JSONActionListener(srcField, showField));
        opPanel.add(trimBtn);

        JButton escapeBtn =
                SmartJView.createButton("转义", ActionCommand.ESCAPE_JAVA);
        escapeBtn.addActionListener(new JSONActionListener(srcField, showField));
        opPanel.add(escapeBtn);

        JButton completeBtn =
                SmartJView.createButton("补充", ActionCommand.JSON_COMPLETE);
        completeBtn.addActionListener(new JSONActionListener(srcField, showField));
        opPanel.add(completeBtn);

        JButton mapBtn =
                SmartJView.createButton("生成Map", ActionCommand.MAP);
        mapBtn.addActionListener(new JSONActionListener(srcField, showField));
        opPanel.add(mapBtn);
    }

}
