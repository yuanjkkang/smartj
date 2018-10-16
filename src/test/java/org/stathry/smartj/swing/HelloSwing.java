package org.stathry.smartj.swing;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 * Created by dongdaiming on 2018-10-15 11:03
 */
public class HelloSwing {

    private static void createHelloGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("helloJFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello,Swing!");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    private static void createLoginGUI() {
        JFrame frame = new JFrame("loginFrame");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createLoginPanel());

        frame.setVisible(true);
    }

    private static Component createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel userLabel = new JLabel("username:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100,20, 165, 25);
        panel.add(userField);

        JLabel pwdLabel = new JLabel("password:");
        pwdLabel.setBounds(10,50,80,25);
        panel.add(pwdLabel);

        JPasswordField pwdField = new JPasswordField();
        pwdField.setBounds(100,50, 165, 25);
        panel.add(pwdField);

        JLabel showLabel = new JLabel("result:");
        showLabel.setBounds(10,80,80,25);
        panel.add(showLabel);

        JTextField showField = new JTextField();
        showField.setBounds(100,80, 165, 25);
        panel.add(showField);

        JButton loginButton = new JButton("login");
        loginButton.setActionCommand("loginCommand");
        loginButton.addActionListener(new LoginActionListener(userField, pwdField, showField));
        loginButton.setBounds(10,110,80,25);
        panel.add(loginButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            createHelloGUI();
            createLoginGUI();
        });
    }

    private static class LoginActionListener implements ActionListener {

        private JTextField userField;
        private JTextField showField;
        private JPasswordField pwdField;

        public LoginActionListener() {
            super();
        }
        public LoginActionListener(JTextField userField, JPasswordField pwdField, JTextField showField) {
            super();
            this.userField = userField;
            this.pwdField = pwdField;
            this.showField = showField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("LoginActionListener.actionPerformed, commandName:" + e.getActionCommand());
            System.out.println(JSON.toJSONString(e.toString()));
            String r = "name:" + userField.getText() + ", pwd:" + new String(pwdField.getPassword());
            System.out.println(r);
            showField.setText(r);
        }
    }
}
