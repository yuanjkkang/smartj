package org.stathry.smartj.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.SystemUtils;
import org.stathry.smartj.commons.utils.SmartJsonUtils;
import org.stathry.smartj.constant.ActionCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * JSONActionListener
 * Created by dongdaiming on 2018-10-16 11:00
 */
public class JSONActionListener implements ActionListener {

    private JTextField srcField;
    private JTextArea showField;
    private static final String LINE_SEP = ";" + SystemUtils.LINE_SEPARATOR;

    public JSONActionListener() {
        super();
    }

    public JSONActionListener(JTextField srcField, JTextArea showField) {
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
            case ActionCommand.MAP:
                JSONObject json = JSON.parseObject(in);
                StringBuilder mapStr = new StringBuilder("Map<String,Object> map = new HashMap<>()");
                mapStr.append(LINE_SEP);
                for (Map.Entry<String, Object> entry : json.entrySet()) {
                    mapStr.append("map.put(\"").append(entry.getKey()).append("\", ")
                            .append("\"").append(String.valueOf(entry.getValue())).append("\")");
                    mapStr.append(LINE_SEP);
                }
                value = mapStr.toString();
                break;
            default:
                System.out.println("not support cmd " + cmd);
                value = "";
        }
        showField.setText(value);
    }
}
