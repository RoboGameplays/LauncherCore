
package net.titanscraft.launchercore.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage scaleWithAspectWidth(BufferedImage img, int width) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        int height = imgHeight * width / imgWidth;
        return scaleImage(img, width, height);
    }

    public static BufferedImage scaleImage(BufferedImage img, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    public static BufferedImage scaleWithAspectHeight(BufferedImage img, int height) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        int width = imgWidth * height / imgHeight;
        return scaleImage(img, width, height);
    }


}