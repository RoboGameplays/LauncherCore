package main.java.net.titanscraft.launchercore.install.user.skins;

import main.java.net.titanscraft.launchercore.install.user.User;

import java.awt.image.BufferedImage;

public interface ISkinMapper {
    String getSkinFilename(User user);

    String getFaceFilename(User user);

    BufferedImage getDefaultSkinImage();

    BufferedImage getDefaultFaceImage();
}
