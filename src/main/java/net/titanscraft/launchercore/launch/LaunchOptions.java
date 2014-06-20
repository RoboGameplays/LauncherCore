
package net.titanscraft.launchercore.launch;

import java.util.List;

public class LaunchOptions {
    private int width;
    private int height;
    private boolean fullscreen;
    private String title;
    private String iconPath;

    public LaunchOptions(String title, String iconPath, int width, int height, boolean fullscreen) {
        this.width = width;
        this.height = height;
        this.fullscreen = fullscreen;
        this.title = title;
        this.iconPath = iconPath;
    }

    public String getTitle() {
        return title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getFullscreen() {
        return fullscreen;
    }

    public void appendToCommands(List<String> commands) {
        if (getTitle() != null) {
            commands.add("--title");
            commands.add(title);
        }

        if (getWidth() > -1) {
            commands.add("--width");
            commands.add(Integer.toString(getWidth()));
        }

        if (getHeight() > -1) {
            commands.add("--height");
            commands.add(Integer.toString(getHeight()));
        }

        if (getFullscreen()) {
            commands.add("--fullscreen");
        }

        if (getIconPath() != null) {
            commands.add("--icon");
            commands.add(getIconPath());
        }
    }
}
