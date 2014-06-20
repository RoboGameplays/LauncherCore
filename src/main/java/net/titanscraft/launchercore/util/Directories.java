
package main.java.net.titanscraft.launchercore.util;

import java.io.File;

public abstract class Directories {
    public static Directories instance;

    public abstract File getLauncherDirectory();

    public abstract File getSettingsDirectory();

    public abstract File getCacheDirectory();

    public abstract File getAssetsDirectory();

    public abstract File getModpacksDirectory();
}
