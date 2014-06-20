package net.titanscraft.launchercore.install.user.skins;

import net.titanscraft.launchercore.install.user.User;

public interface ISkinStore {
    void downloadUserSkin(User user, String location);

    void downloadUserFace(User user, String location);
}
