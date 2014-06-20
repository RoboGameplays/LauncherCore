
package main.java.net.titanscraft.launchercore.minecraft.versionlist;

import main.java.net.titanscraft.launchercore.minecraft.ReleaseType;

import java.util.Date;

public class VersionEntry {
    private String id;
    private Date time;
    private Date releaseTime;
    private ReleaseType type;

    public String getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public ReleaseType getType() {
        return type;
    }
}
