package net.titanscraft.launchercore.install.tasks;

import net.titanscraft.launchercore.install.InstalledPack;

import java.io.IOException;

public class InitPackDirectoryTask implements IInstallTask {
    private InstalledPack pack;

    public InitPackDirectoryTask(InstalledPack pack) {
        this.pack = pack;
    }

    @Override
    public String getTaskDescription() {
        return "Criando Pastas";
    }

    @Override
    public float getTaskProgress() {
        return 0;
    }

    @Override
    public void runTask(InstallTasksQueue queue) throws IOException {
        this.pack.getInstalledDirectory();
        this.pack.initDirectories();
    }
}
