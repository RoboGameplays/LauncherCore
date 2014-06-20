package net.titanscraft.launchercore.install.tasks;

import java.io.IOException;

public interface IInstallTask {
    String getTaskDescription();

    float getTaskProgress();

    void runTask(InstallTasksQueue queue) throws IOException;
}
