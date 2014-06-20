
package net.titanscraft.launchercore.restful;

public class PlatformConstants {
    public static final String PLATFORM = "http://www.titanscraft.net/";

    public static final String API = PLATFORM + "api/";

    public static final String MODPACK = API + "modpack/";

    public static final String NEWS = API + "news/";

    public static String getPlatformInfoUrl(String modpack) {
        return MODPACK + modpack;
    }

    public static String getRunCountUrl(String modpack) {
        return getPlatformInfoUrl(modpack) + "/run";
    }

    public static String getDownloadCountUrl(String modpack) {
        return getPlatformInfoUrl(modpack) + "/download";
    }
}
