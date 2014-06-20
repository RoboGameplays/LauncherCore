
package net.titanscraft.launchercore.launch;

import java.util.List;

public class MinecraftProcess {
    private final List<String> commands;
    private final Process process;
    private MinecraftExitListener exitListener;
    private final ProcessMonitorThread monitorThread;

    public MinecraftProcess(List<String> commands, Process process) {
        this.commands = commands;
        this.process = process;
        this.monitorThread = new ProcessMonitorThread(this);
        this.monitorThread.start();
    }

    public MinecraftExitListener getExitListener() {
        return exitListener;
    }

    public void setExitListener(MinecraftExitListener exitListener) {
        this.exitListener = exitListener;
    }

    public Process getProcess() {
        return process;
    }
}
