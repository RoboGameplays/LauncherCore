
package main.java.net.titanscraft.launchercore.minecraft;

public class MojangConstants {
    public static final String baseURL = "https://s3.amazonaws.com/Minecraft.Download/";
    public static final String assetsIndexes = baseURL + "indexes/";
    public static final String versions = baseURL + "versions/";
    public static final String assets = "http://resources.download.minecraft.net/";
    public static final String versionList = versions + "versions.json";

    public static String getVersionJson(String version) {
        return versions + version + "/" + version + ".json";
    }

    public static String getVersionDownload(String version) {
        return versions + version + "/" + version + ".jar";
    }

    public static String getAssetsIndex(String assetsKey) {
        return assetsIndexes + assetsKey + ".json";
    }

    public static String getResourceUrl(String hash) {
        return assets + hash.substring(0, 2) + "/" + hash;
    }
}
