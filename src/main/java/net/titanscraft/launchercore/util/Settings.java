
package net.titanscraft.launchercore.util;

import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.logging.Level;

public class Settings {
    public static final String STABLE = "stable";
    public static final String BETA = "beta";
    public static Settings instance = new Settings();
    private String directory;
    private int memory;
    private LaunchAction launchAction;
    private String buildStream = STABLE;
    private boolean showConsole;
    private String languageCode = "default";
    private boolean migrate;
    private String clientId = UUID.randomUUID().toString();
    private String migrateDir;

    public static void load() {
        File settings = new File(Utils.getSettingsDirectory(), "settings.json");
        if (!settings.exists()) {
            Utils.getLogger().log(Level.WARNING, "Unable to load settings from " + settings + " because it does not exist.");
            return;
        }

        try {
            String json = FileUtils.readFileToString(settings, Charset.forName("UTF-8"));
            instance = Utils.getGson().fromJson(json, Settings.class);
        } catch (JsonSyntaxException e) {
            Utils.getLogger().log(Level.WARNING, "Unable to load settings from " + settings);
        } catch (IOException e) {
            Utils.getLogger().log(Level.WARNING, "Unable to load settings from " + settings);
        }
    }

    public static String getDirectory() {
        return instance.directory;
    }

    public static void setDirectory(String directory) {
        instance.directory = directory;
        save();
    }

    public static void save() {
        File settings = new File(Utils.getSettingsDirectory(), "settings.json");

        String json = Utils.getGson().toJson(instance);

        try {
            FileUtils.writeStringToFile(settings, json, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Utils.getLogger().log(Level.WARNING, "Unable to save settings " + settings, e);
        }
    }

    public static int getMemory() {
        return instance.memory;
    }

    public static void setMemory(int memory) {
        instance.memory = memory;
        save();
    }

    public static LaunchAction getLaunchAction() {
        return instance.launchAction;
    }

    public static void setLaunchAction(LaunchAction launchAction) {
        instance.launchAction = launchAction;
        save();
    }

    public static String getBuildStream() {
        return instance.buildStream;
    }

    public static void setBuildStream(String buildStream) {
        instance.buildStream = buildStream;
        save();
    }

    public static String getClientId() {
        return instance.clientId;
    }

    public static boolean getShowConsole() {
        return instance.showConsole;
    }

    public static void setShowConsole(boolean showConsole) {
        instance.showConsole = showConsole;
        save();
    }

    public static String getLanguageCode() {
        return instance.languageCode;
    }

    public static void setLanguageCode(String languageCode) {
        instance.languageCode = languageCode;
        save();
    }

    public static boolean getMigrate() {
        return instance.migrate;
    }

    public static void setMigrate(boolean migrate) {
        instance.migrate = migrate;
        save();
    }

    public static String getMigrateDir() {
        return instance.migrateDir;
    }

    public static void setMigrateDir(String migrateDir) {
        instance.migrateDir = migrateDir;
        save();
    }

    @Override
    public String toString() {
        return "Settings{" +
                "directory='" + directory + '\'' +
                ", memory=" + memory +
                ", buildStream='" + buildStream + '\'' +
                ", showConsole=" + showConsole +
                ", migrate=" + migrate +
                ", migrateDir='" + migrateDir + '\'' +
                ", launchAction='" + launchAction + '\'' +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}
