
package net.titanscraft.launchercore.restful.platform;

import net.titanscraft.launchercore.exception.BuildInaccessibleException;
import net.titanscraft.launchercore.exception.RestfulAPIException;
import net.titanscraft.launchercore.install.user.User;
import net.titanscraft.launchercore.restful.*;

import java.util.ArrayList;
import java.util.List;

public class PlatformPackInfo extends RestObject implements PackInfo {
    private String name;
    private String displayName;
    private String url;
    private Resource icon;
    private Resource logo;
    private Resource background;
    private String minecraft;
    private String forge;
    private String version;
    private String solder;
    private boolean forceDir;

    public PlatformPackInfo() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Resource getIcon() {
        return icon;
    }

    @Override
    public Resource getBackground() {
        return background;
    }

    @Override
    public Resource getLogo() {
        return logo;
    }

    @Override
    public String getRecommended() {
        return version;
    }

    @Override
    public String getLatest() {
        return version;
    }

    @Override
    public boolean shouldForceDirectory() {
        return forceDir;
    }

    @Override
    public List<String> getBuilds() {
        List<String> builds = new ArrayList<String>();
        builds.add(version);
        return builds;
    }

    public String getMinecraft() {
        return minecraft;
    }

    public String getForge() {
        return forge;
    }

    public String getSolder() {
        return solder;
    }

    public boolean hasSolder() {
        return solder != null && !solder.equals("");
    }

    @Override
    public Modpack getModpack(String build, User user) throws BuildInaccessibleException {
        return new Modpack(this);
    }

    @Override
    public String toString() {
        return "PlatformPackInfo{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", url='" + url + '\'' +
                ", icon=" + icon +
                ", logo=" + logo +
                ", background=" + background +
                ", minecraft='" + minecraft + '\'' +
                ", forge='" + forge + '\'' +
                ", version='" + version + '\'' +
                ", solder='" + solder + '\'' +
                ", forceDir=" + forceDir +
                '}';
    }

    public static PlatformPackInfo getPlatformPackInfo(String name) throws RestfulAPIException {
        return getRestObject(PlatformPackInfo.class, PlatformConstants.getPlatformInfoUrl(name));
    }
}
