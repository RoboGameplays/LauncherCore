package net.titanscraft.launchercore.install;

import net.titanscraft.launchercore.exception.CacheDeleteException;
import net.titanscraft.launchercore.exception.PackNotAvailableOfflineException;
import net.titanscraft.launchercore.install.tasks.*;
import net.titanscraft.launchercore.install.user.User;
import net.titanscraft.launchercore.minecraft.CompleteVersion;
import net.titanscraft.launchercore.mirror.MirrorStore;
import net.titanscraft.launchercore.restful.Modpack;
import net.titanscraft.launchercore.restful.PackInfo;
import net.titanscraft.launchercore.restful.PlatformConstants;
import net.titanscraft.launchercore.util.DownloadListener;
import net.titanscraft.launchercore.util.Utils;
import net.titanscraft.launchercore.util.ZipUtils;
import net.titanscraft.launchercore.util.verifiers.ValidZipFileVerifier;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ModpackInstaller {
    private final DownloadListener listener;
    private final InstalledPack installedPack;
    private String build;
    private boolean finished = false;
    private MirrorStore mirrorStore;

    public ModpackInstaller(DownloadListener listener, InstalledPack installedPack, String build, MirrorStore mirrorStore) {
        this.listener = listener;
        this.installedPack = installedPack;
        this.build = build;
        this.mirrorStore = mirrorStore;
    }

    public CompleteVersion installPack(Component component, User user) throws IOException {
        InstallTasksQueue queue = new InstallTasksQueue(this.listener, mirrorStore);
        queue.AddTask(new InitPackDirectoryTask(this.installedPack));

        PackInfo packInfo = this.installedPack.getInfo();
        Modpack modpack = packInfo.getModpack(this.build, user);
        String minecraft = modpack.getMinecraft();

        if (minecraft.startsWith("1.5")) {
            queue.AddTask(new EnsureFileTask(new File(Utils.getCacheDirectory(), "fml_libs15.zip"), new ValidZipFileVerifier(), new File(installedPack.getInstalledDirectory(), "lib"), "http://titanscraft.net/Technic/lib/fml/fml_libs15.zip"));
        } else if (minecraft.startsWith("1.4")) {
            queue.AddTask(new EnsureFileTask(new File(Utils.getCacheDirectory(), "fml_libs.zip"), new ValidZipFileVerifier(), new File(installedPack.getInstalledDirectory(), "lib"), "http://titanscraft.net/Technic/lib/fml/fml_libs.zip"));
        }

        queue.RunAllTasks();
        Version installedVersion = this.getInstalledVersion();

        boolean shouldUpdate = installedVersion == null;
        if (!shouldUpdate && !this.build.equals(installedVersion.getVersion())) {
            int result = JOptionPane.showConfirmDialog(component, "Voce gostaria de atualizar o modpack?", "Atualização disponivel", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                shouldUpdate = true;
            } else {
                build = installedVersion.getVersion();
            }
        }

        if (shouldUpdate) {
            //If we're installing a new version of modpack, then we need to get rid of the existing version.json
            File versionFile = new File(installedPack.getBinDir(), "version.json");
            if (versionFile.exists()) {
                if (!versionFile.delete()) {
                    throw new CacheDeleteException(versionFile.getAbsolutePath());
                }
            }

            queue.AddTask(new InstallModpackTask(this.installedPack, modpack));
        }

        queue.AddTask(new VerifyVersionFilePresentTask(installedPack, minecraft));
        queue.AddTask(new HandleVersionFileTask(installedPack));

        if ((installedVersion != null && installedVersion.isLegacy()) || shouldUpdate)
            queue.AddTask(new InstallMinecraftIfNecessaryTask(this.installedPack, minecraft));

        queue.RunAllTasks();

        Version versionFile = new Version(build, false);
        versionFile.save(installedPack.getBinDir());

        finished = true;
        return queue.getCompleteVersion();
    }

    private Version getInstalledVersion() {
        Version version = null;
        File versionFile = new File(this.installedPack.getBinDir(), "version");
        if (versionFile.exists()) {
            version = Version.load(versionFile);
        } else {
            Utils.pingHttpURL(PlatformConstants.getDownloadCountUrl(this.installedPack.getName()), mirrorStore);
            Utils.sendTracking("installModpack", this.installedPack.getName(), this.installedPack.getBuild());
        }
        return version;
    }

    public boolean isFinished() {
        return finished;
    }

    public CompleteVersion prepareOfflinePack() throws IOException {
        installedPack.getInstalledDirectory();
        installedPack.initDirectories();

        File versionFile = new File(installedPack.getBinDir(), "version.json");
        File modpackJar = new File(installedPack.getBinDir(), "modpack.jar");

        boolean didExtract = false;

        if (modpackJar.exists()) {
            didExtract = ZipUtils.extractFile(modpackJar, installedPack.getBinDir(), "version.json");
        }

        if (!versionFile.exists()) {
            throw new PackNotAvailableOfflineException(installedPack.getDisplayName());
        }

        String json = FileUtils.readFileToString(versionFile, Charset.forName("UTF-8"));
        return Utils.getMojangGson().fromJson(json, CompleteVersion.class);
    }
}
