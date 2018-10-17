package org.stathry.smartj.view;

import org.stathry.smartj.constant.SwingConstant;
import org.stathry.smartj.model.LabelPosition;

import javax.swing.*;
import java.awt.*;

/**
 * MybatisView
 * Created by dongdaiming on 2018-10-16 16:21
 */
public class MybatisView {

    public static JPanel createPanel(String panelTitle) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(panelTitle));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel urlp = new JPanel();
        urlp.setLayout(new BoxLayout(urlp, BoxLayout.X_AXIS));

        JLabel urlLabel = new JLabel("jdbc.url:");
        SmartJView.fixLabelSize(urlLabel);
        urlp.add(urlLabel);

        JTextField urlField = new JTextField();
        urlField.setToolTipText("jdbc:mysql://localhost:3306/test");
        urlp.add(urlField);
        panel.add(urlp);

        JPanel namep = new JPanel();
        namep.setLayout(new BoxLayout(namep, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel("jdbc.username:");
        SmartJView.fixLabelSize(nameLabel);
        namep.add(nameLabel);

        JTextField nameField = new JTextField();
        namep.add(nameField);
        panel.add(namep);

        JPanel pwdp = new JPanel();
        pwdp.setLayout(new BoxLayout(pwdp, BoxLayout.X_AXIS));
        JLabel pwdLabel = new JLabel("jdbc.password:");
        SmartJView.fixLabelSize(pwdLabel);
        pwdp.add(pwdLabel);

        JPasswordField pwdField = new JPasswordField();
        pwdp.add(pwdField);
        panel.add(pwdp);

        JPanel tablep = new JPanel();
        tablep.setLayout(new BoxLayout(tablep, BoxLayout.X_AXIS));
        JLabel tableLabel = new JLabel("tables:");
        SmartJView.fixLabelSize(tableLabel);
        tablep.add(tableLabel);

        JTextField tableField = new JTextField();
        tableField.setToolTipText("多张表以英文逗号分隔！");
        tablep.add(tableField);
        panel.add(tablep);

        JLabel showLabel = new JLabel("output:");
        panel.add(showLabel);

        JTextArea showField = new JTextArea();
        panel.add(showField);

//        fPanel.setAlignmentY(Component.TOP_ALIGNMENT);
//        fPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

//        panel.add(fPanel);
        return panel;
    }

    public static JPanel createPanel2(String panelTitle) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(panelTitle));
        LayoutManager layout = new GridLayout(0, 2);
        panel.setLayout(layout);

        JLabel urlLabel = new JLabel("jdbc.url:");
        SmartJView.fixLabelSize(urlLabel);
        panel.add(urlLabel);

        JTextField urlField = new JTextField();
        urlField.setToolTipText("jdbc:mysql://localhost:3306/test");
        panel.add(urlField);

        JLabel nameLabel = new JLabel("jdbc.username:");
        SmartJView.fixLabelSize(nameLabel);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        panel.add(nameField);

        JLabel pwdLabel = new JLabel("jdbc.password:");
        SmartJView.fixLabelSize(pwdLabel);
        panel.add(pwdLabel);

        JPasswordField pwdField = new JPasswordField();
        panel.add(pwdField);

        JLabel tableLabel = new JLabel("tables:");
        SmartJView.fixLabelSize(tableLabel);
        panel.add(tableLabel);

        JTextField tableField = new JTextField();
        tableField.setToolTipText("多张表以英文逗号分隔！");
        panel.add(tableField);

        JLabel showLabel = new JLabel("提示:");
        panel.add(showLabel);

        JTextArea showField = new JTextArea();
        panel.add(showField);

        return panel;
    }

}
