
package main.java.net.titanscraft.launchercore.restful.solder;

import main.java.net.titanscraft.launchercore.restful.RestObject;

import java.util.LinkedHashMap;

public class FullModpacks extends RestObject {

    private LinkedHashMap<String, SolderPackInfo> modpacks;
    private String mirror_url;

    public LinkedHashMap<String, SolderPackInfo> getModpacks() {
        return modpacks;
    }

    public String getMirrorUrl() {
        return mirror_url;
    }
}
