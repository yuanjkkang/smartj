package org.stathry.smartj.view;

import org.stathry.smartj.constant.ActionCommand;
import org.stathry.smartj.listener.MyBatisActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * MybatisView
 * Created by dongdaiming on 2018-10-16 16:21
 */
public class MybatisView {

    public static JPanel createPanel(String panelTitle) {
        JPanel c = new JPanel();
        c.setBorder(BorderFactory.createTitledBorder(panelTitle));
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        c.add(panel);
        LayoutManager layout = new GridLayout(0, 2);
        panel.setLayout(layout);

        JLabel classLabel = new JLabel("jdbc.driverClassName");
        panel.add(classLabel);

        JTextField classField = new JTextField();
        classField.setText("com.mysql.jdbc.Driver");
        panel.add(classField);

        JLabel urlLabel = new JLabel("jdbc.url");
        panel.add(urlLabel);

        JTextField urlField = new JTextField("jdbc:mysql://localhost:3306/world");
        panel.add(urlField);

        JLabel nameLabel = new JLabel("jdbc.username");
        panel.add(nameLabel);

        JTextField nameField = new JTextField("root");
        panel.add(nameField);

        JLabel pwdLabel = new JLabel("jdbc.password");
        panel.add(pwdLabel);

        JPasswordField pwdField = new JPasswordField("root");
        panel.add(pwdField);

        JLabel tableLabel = new JLabel("tables(默认为所有表,多张表以,分隔)");
        panel.add(tableLabel);

        JTextField tableField = new JTextField();
        panel.add(tableField);

        JLabel pkgLabel = new JLabel("项目顶级package");
        panel.add(pkgLabel);

        JTextField pkgField = new JTextField("com.github.demo");
        panel.add(pkgField);

        JLabel jpaLabel = new JLabel("是否生成JPA model");
        panel.add(jpaLabel);

//        JCheckBox jpaField = new JCheckBox("生成JPA model");
//        jpaField.getModel().isSelected();
//        panel.add(jpaField);

        JTextArea showField = new JTextArea();
        JLabel showLabel = new JLabel("提示:");
        createButtons(c, classField, urlField, nameField, pwdField, tableField, pkgField, showField);

        c.add(showLabel);
        c.add(showField);

        return c;
    }

    private static void createButtons(JPanel panel, JTextField classField, JTextField urlField, JTextField nameField, JPasswordField pwdField,
                                      JTextField tableField, JTextField pkgField, JTextArea showField) {
        JPanel opPanel = new JPanel();
        panel.add(opPanel);

        JButton testBtn =
                SmartJView.createButton("测试连通", ActionCommand.MYBATIS_TEST);
        testBtn.addActionListener(new MyBatisActionListener(classField, urlField, nameField, pwdField, tableField, pkgField, showField));
        opPanel.add(testBtn);

        JButton genBtn = SmartJView.createButton("生成MyBatis", ActionCommand.MYBATIS_GENERATE);
        genBtn.addActionListener(new MyBatisActionListener(classField, urlField, nameField, pwdField, tableField, pkgField, showField));
        opPanel.add(genBtn);

        JButton jpaBtn = SmartJView.createButton("生成JPA", ActionCommand.JPA_GENERATE);
        jpaBtn.addActionListener(new MyBatisActionListener(classField, urlField, nameField, pwdField, tableField, pkgField, showField));
        opPanel.add(jpaBtn);
    }

}
