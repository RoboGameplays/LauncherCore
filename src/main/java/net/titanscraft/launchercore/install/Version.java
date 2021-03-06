
package net.titanscraft.launchercore.install;

import com.google.gson.JsonSyntaxException;
import net.titanscraft.launchercore.util.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;

public class Version {
    private String version;
    private boolean legacy;

    public Version() {

    }

    public Version(String version, boolean legacy) {
        this.version = version;
        this.legacy = legacy;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    public static Version load(File version) {
        if (!version.exists()) {
            Utils.getLogger().log(Level.WARNING, "Não foi possível carregar a versão de " + version + " porque não existe.");
            return null;
        }

        try {
            String json = FileUtils.readFileToString(version, Charset.forName("UTF-8"));
            return Utils.getGson().fromJson(json, Version.class);
        } catch (JsonSyntaxException e) {
            Utils.getLogger().log(Level.WARNING, "Não foi possível carregar a versão de " + version);
            return null;
        } catch (IOException e) {
            Utils.getLogger().log(Level.WARNING, "Não foi possível carregar a versão de " + version);
            return null;
        }
    }

    public void save(File saveDirectory) {
        File version = new File(saveDirectory, "version");
        String json = Utils.getGson().toJson(this);

        try {
            FileUtils.writeStringToFile(version, json, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Utils.getLogger().log(Level.WARNING, "Unable to save installed " + version);
        }
    }

    @Override
    public String toString() {
        return version + " " + legacy;
    }
}
