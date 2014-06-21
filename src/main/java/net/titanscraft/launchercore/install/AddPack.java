
package net.titanscraft.launchercore.install;

import net.titanscraft.launchercore.util.ResourceUtils;

import java.awt.image.BufferedImage;

public class AddPack extends InstalledPack {
    private final static BufferedImage icon = ResourceUtils.getImage("icon.png", 32, 32);
    private final static BufferedImage logo = ResourceUtils.getImage("addNewPack.png", 180, 110);
    private final static BufferedImage background = ResourceUtils.getImage("background.jpg", 880, 520);

    public AddPack() {
        super();
    }

    @Override
    public synchronized BufferedImage getIcon() {
        return icon;
    }

    @Override
    public synchronized BufferedImage getBackground() {
        return background;
    }

    @Override
    public synchronized BufferedImage getLogo() {
        return logo;
    }

    @Override
    public String getName() {
        return "addpack";
    }

    @Override
    public String getDisplayName() {
        return "Adicionar Pack";
    }
}
