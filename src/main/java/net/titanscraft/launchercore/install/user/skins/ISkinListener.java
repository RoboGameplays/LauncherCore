package main.java.net.titanscraft.launchercore.install.user.skins;

import main.java.net.titanscraft.launchercore.install.user.User;

public interface ISkinListener {
    void skinReady(User user);

    void faceReady(User user);
}
