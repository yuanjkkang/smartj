package org.stathry.smartj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.stathry.smartj.view.JSONView;
import org.stathry.smartj.view.MybatisView;
import org.stathry.smartj.view.SmartJView;

import javax.swing.*;
import java.awt.*;

/**
 * SmartJServer
 * Created by dongdaiming on 2018-10-16 10:44
 */
public class SmartJServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartJServer.class);

    public static void main(String[] args) {
        startSpringContext();

        showSmartJClient();

        while (true) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void startSpringContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        context.start();
        LOGGER.info("SmartJServer started.");
    }

    private static void showSmartJClient() {
        SmartJView.initGlobalFont(new Font("新宋体", Font.PLAIN, 12));

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridBagLayout());

        JPanel leftPanel = MybatisView.createPanel("MyBatis生成工具");
        GridBagConstraints leftC = SmartJView.createGridBagConstraints(0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        panelContainer.add(leftPanel, leftC);

        JPanel rightPanel = JSONView.createPanel("JSON工具");
        GridBagConstraints rightC = SmartJView.createGridBagConstraints(1, 0, 1.0, 1.0, GridBagConstraints.BOTH);
        panelContainer.add(rightPanel, rightC);

        panelContainer.setOpaque(true);

        JFrame frame = SmartJView.createMainFrame("SmartJServer", 880, 600);
        frame.setContentPane(panelContainer);
        frame.setVisible(true);
    }

}
