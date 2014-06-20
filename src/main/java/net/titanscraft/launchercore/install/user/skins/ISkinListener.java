package net.titanscraft.launchercore.install.user.skins;

import net.titanscraft.launchercore.install.user.User;

public interface ISkinListener {
    void skinReady(User user);

    void faceReady(User user);
}
