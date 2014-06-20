
package net.titanscraft.launchercore.restful;

import net.titanscraft.launchercore.restful.platform.PlatformPackInfo;
import net.titanscraft.launchercore.restful.solder.Mod;

import java.util.ArrayList;
import java.util.List;

public class Modpack extends RestObject {
    private String minecraft;
    private List<Mod> mods;

    public Modpack() {

    }

    public Modpack(PlatformPackInfo info) {
        minecraft = info.getMinecraft();
        mods = new ArrayList<Mod>();
        Mod mod = new Mod(info.getName(), info.getRecommended(), info.getUrl(), "");
        mods.add(mod);
    }

    public String getMinecraft() {
        return minecraft;
    }

    public List<Mod> getMods() {
        return mods;
    }
}
