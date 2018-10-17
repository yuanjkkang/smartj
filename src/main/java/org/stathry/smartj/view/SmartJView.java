package org.stathry.smartj.view;

import org.stathry.smartj.constant.SwingConstant;

import javax.swing.*;
import java.awt.*;

/**
 * SmartJView
 * Created by dongdaiming on 2018-10-16 10:49
 */
public class SmartJView {

    private SmartJView() {}

    public static JFrame createMainFrame(String frameName, int width, int height) {
        JFrame frame = new JFrame(frameName);
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    public static JButton createButton(String buttonText, String command) {
        JButton btn = new JButton(buttonText);
        btn.setActionCommand(command);
        return btn;
    }

    public static JTextField createFixSizedTextField(String text) {
        JTextField field = new JTextField();
        Dimension dimension = new Dimension(SwingConstant.FIELD_WIDTH, SwingConstant.FIELD_HEIGHT);
        field.setPreferredSize(dimension);
        field.setMinimumSize(dimension);
        field.setMaximumSize(dimension);
        return field;
    }

    public static GridBagConstraints createGridBagConstraints(int gridx, int gridy, double weightx, double weighty, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
        return c;
    }

    public static void fixLabelSize(Component component) {
        component.setSize(SwingConstant.LABEL_WIDTH, SwingConstant.LABEL_HEIGHT);
    }

    public static void fixFieldSize(Component component) {
        component.setSize(SwingConstant.FIELD_WIDTH, SwingConstant.FIELD_HEIGHT);
    }

}
