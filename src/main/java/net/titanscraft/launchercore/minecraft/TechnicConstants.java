package net.titanscraft.launchercore.minecraft;

public class TechnicConstants {
    public static final String technicURL = "http://www.titanscraft.net/Technic/";
    public static final String technicVersions = technicURL + "version/";

    public static String getTechnicVersionJson(String version) {
        return technicVersions + version + "/" + version + ".json";
    }
}
