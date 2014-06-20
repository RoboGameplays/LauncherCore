
package net.titanscraft.launchercore.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessMonitorThread extends Thread {

    private final MinecraftProcess process;

    public ProcessMonitorThread(MinecraftProcess process) {
        super("ProcessMonitorThread");
        this.process = process;
    }

    public void run() {
        InputStreamReader reader = new InputStreamReader(this.process.getProcess().getInputStream());
        BufferedReader buf = new BufferedReader(reader);
        String line = null;

        while (true) {
            try {
                while ((line = buf.readLine()) != null) {
                    System.out.println(" " + line);
                }
            } catch (IOException ex) {
//				Logger.getLogger(ProcessMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    buf.close();
                } catch (IOException ex) {
//					Logger.getLogger(ProcessMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        process.getProcess().waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        if (process.getExitListener() != null) {
            process.getExitListener().onMinecraftExit();
        }
    }
}
