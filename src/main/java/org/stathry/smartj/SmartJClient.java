package org.stathry.smartj;

import org.stathry.smartj.view.JSONView;
import org.stathry.smartj.view.MybatisView;
import org.stathry.smartj.view.SmartJView;

import javax.swing.*;
import java.awt.*;

/**
 * SmartJClient
 * Created by dongdaiming on 2018-10-16 10:44
 */
public class SmartJClient {

    public static void main(String[] args) {
        startSmartJClient();
    }

    private static void startSmartJClient() {
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridBagLayout());

        JPanel leftPanel = MybatisView.createPanel2("MyBatis工具");
        GridBagConstraints leftC = SmartJView.createGridBagConstraints(0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        panelContainer.add(leftPanel, leftC);

        JPanel rightPanel = JSONView.createPanel("JSON工具");
        GridBagConstraints rightC = SmartJView.createGridBagConstraints(1, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        panelContainer.add(rightPanel, rightC);

        panelContainer.setOpaque(true);

        JFrame frame = SmartJView.createMainFrame("SmartJClient", 800, 600);
        frame.setContentPane(panelContainer);
        frame.setVisible(true);
    }


}
