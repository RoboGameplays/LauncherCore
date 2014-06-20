
package net.titanscraft.launchercore.minecraft.versionlist;

import java.util.List;

public class VersionList {

    private List<VersionEntry> versions;
    private LatestEntry latest;

    public List<VersionEntry> getVersions() {
        return versions;
    }

    public LatestEntry getLatest() {
        return latest;
    }
}
