
package net.titanscraft.launchercore.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ResourceUtils {

    public static File getResourceAsFile(String path) {
        File file = new File(".\\src\\main\\resources\\" + path);
        if (file.exists())
            return file;
        else
            return null;
    }

    public static ImageIcon getIcon(String iconName) {
        return new ImageIcon(ResourceUtils.class.getResource("/org/spoutcraft/launcher/resources/" + iconName));
    }

    public static BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getResourceAsStream("/org/spoutcraft/launcher/resources/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream getResourceAsStream(String path) {
        InputStream stream = ResourceUtils.class.getResourceAsStream(path);
        String[] split = path.split("/");
        path = split[split.length - 1];
        if (stream == null) {
            File resource = new File(".\\src\\main\\resources\\" + path);
            if (resource.exists()) {
                try {
                    stream = new BufferedInputStream(new FileInputStream(resource));
                } catch (IOException ignore) {
                }
            }
        }
        return stream;
    }

    public static BufferedImage getImage(String imageName, int w, int h) {
        try {
            return ImageUtils.scaleImage(ImageIO.read(getResourceAsStream("/org/spoutcraft/launcher/resources/" + imageName)), w, h);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getIcon(String iconName, int w, int h) {
        try {
            return new ImageIcon(ImageUtils.scaleImage(ImageIO.read(getResourceAsStream("/org/spoutcraft/launcher/resources/" + iconName)), w, h));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
