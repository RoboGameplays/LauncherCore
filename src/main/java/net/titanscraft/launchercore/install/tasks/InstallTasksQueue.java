package main.java.net.titanscraft.launchercore.install.tasks;

import main.java.net.titanscraft.launchercore.minecraft.CompleteVersion;
import main.java.net.titanscraft.launchercore.mirror.MirrorStore;
import main.java.net.titanscraft.launchercore.util.DownloadListener;

import java.io.IOException;
import java.util.LinkedList;

public class InstallTasksQueue {
    private DownloadListener listener;
    private LinkedList<IInstallTask> tasks;
    private IInstallTask currentTask;
    private CompleteVersion completeVersion;
    private MirrorStore mirrorStore;

    public InstallTasksQueue(DownloadListener listener, MirrorStore mirrorStore) {
        this.listener = listener;
        this.mirrorStore = mirrorStore;
        this.tasks = new LinkedList<IInstallTask>();
        this.currentTask = null;
    }

    public void RefreshProgress() {
        listener.stateChanged(currentTask.getTaskDescription(), currentTask.getTaskProgress());
    }

    public void RunAllTasks() throws IOException {
        while (!tasks.isEmpty()) {
            currentTask = tasks.removeFirst();
            RefreshProgress();
            currentTask.runTask(this);
        }
    }

    public void AddNextTask(IInstallTask task) {
        tasks.addFirst(task);
    }

    public void AddTask(IInstallTask task) {
        tasks.addLast(task);
    }

    public void setCompleteVersion(CompleteVersion version) {
        this.completeVersion = version;
    }

    public CompleteVersion getCompleteVersion() {
        return this.completeVersion;
    }

    public MirrorStore getMirrorStore() {
        return this.mirrorStore;
    }
}
