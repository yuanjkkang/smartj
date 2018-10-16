package org.stathry.smartj;

import org.stathry.smartj.controller.SmartJController;
import org.stathry.smartj.model.LabelPosition;

import javax.swing.*;

/**
 * SmartJClient
 * Created by dongdaiming on 2018-10-16 10:44
 */
public class SmartJClient {

    public static void main(String[] args) {
        startSmartJClient();
    }

    private static void startSmartJClient() {
        JFrame frame = SmartJController.createMainFrame("SmartJClient", 1600, 1200);
        JPanel panel = SmartJController.createJSONHandlePanel(new LabelPosition(10, 20, 100, 20), "JSON工具");
        frame.add(panel);
        frame.setVisible(true);
    }
}
