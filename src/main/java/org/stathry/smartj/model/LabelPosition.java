package org.stathry.smartj.model;

/**
 * TODO
 * Created by dongdaiming on 2018-10-16 11:40
 */
public class LabelPosition {
    private int labelX, labelY, fieldX, fieldY;

    private final int labelXDelta = 120;
    private final int labelYDelta = 30;
    private final int fieldXDelta = 240;
    private final int fieldYDelta = 30;

    public LabelPosition() {
    }

    public LabelPosition(int labelX, int labelY, int fieldX, int fieldY) {
        this.labelX = labelX;
        this.labelY = labelY;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    @Override
    public String toString() {
        return "LabelPosition{" +
                "labelX=" + labelX +
                ", labelY=" + labelY +
                ", fieldX=" + fieldX +
                ", fieldY=" + fieldY +
                ", labelXDelta=" + labelXDelta +
                ", labelYDelta=" + labelYDelta +
                ", fieldXDelta=" + fieldXDelta +
                ", fieldYDelta=" + fieldYDelta +
                '}';
    }

    public int nextLabelX() {
        return labelX = labelX + labelXDelta;
    }

    public int nextLabelY() {
        return labelY = labelY + labelYDelta;
    }

    public int nextFieldX() {
        return fieldX = fieldX + fieldXDelta;
    }

    public int nextFieldY() {
        return fieldY = fieldY + fieldYDelta;
    }

    public int getLabelX() {
        return labelX;
    }

    public void setLabelX(int labelX) {
        this.labelX = labelX;
    }

    public int getLabelY() {
        return labelY;
    }

    public void setLabelY(int labelY) {
        this.labelY = labelY;
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fieldX) {
        this.fieldX = fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fieldY) {
        this.fieldY = fieldY;
    }
}
