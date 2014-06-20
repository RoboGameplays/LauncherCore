
package main.java.net.titanscraft.launchercore.restful.solder;

import main.java.net.titanscraft.launchercore.util.Settings;

public class SolderConstants {
    public static final String TECHNIC = "http://solder.technicpack.net/api/";

    public static String getSolderPackInfoUrl(String solder, String modpack, String profileName) {
        return solder + "modpack/" + modpack + "/?cid=" + Settings.getClientId() + "&u=" + profileName;
    }

    public static String getSolderPackInfoUrlWithoutCid(String solder, String modpack) {
        return solder + "modpack/" + modpack + "/";
    }

    public static String getSolderBuildUrl(String solder, String modpack, String build, String profileName) {
        return getSolderPackInfoUrlWithoutCid(solder, modpack) + build + "/?cid=" + Settings.getClientId() + "&u=" + profileName;
    }

    public static String getFullSolderUrl(String solder, String profileName) {
        return solder + "modpack/?include=full&cid=" + Settings.getClientId() + "&u=" + profileName;
    }
}
