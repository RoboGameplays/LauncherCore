
package main.java.net.titanscraft.launchercore.restful;


import main.java.net.titanscraft.launchercore.exception.BuildInaccessibleException;
import main.java.net.titanscraft.launchercore.install.user.User;

import java.util.List;

public interface PackInfo {

    public String getName();

    public String getDisplayName();

    public String getUrl();

    public Resource getIcon();

    public Resource getBackground();

    public Resource getLogo();

    public String getRecommended();

    public String getLatest();

    public List<String> getBuilds();

    public boolean shouldForceDirectory();

    public Modpack getModpack(String build, User user) throws BuildInaccessibleException;
}
