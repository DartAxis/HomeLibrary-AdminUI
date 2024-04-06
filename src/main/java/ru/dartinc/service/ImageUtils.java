package ru.dartinc.service;

import java.awt.*;

public class ImageUtils {
    private static Image scaleButtonImage(Component component, Dimension dimension, Image image) {
        double width = image.getWidth(component);
        double height = image.getHeight(component);
        double xScale = dimension.getWidth() / width;
        double yScale = dimension.getHeight() / height;
        double scale = Math.min(xScale, yScale);
        //double Scale = Math.max(xScale, yScale); //ToFill
        return image.getScaledInstance((int) (scale * width), (int) (scale * height), java.awt.Image.SCALE_SMOOTH);
    }
}
