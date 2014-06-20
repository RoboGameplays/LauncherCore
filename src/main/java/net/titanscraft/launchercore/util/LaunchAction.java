
package net.titanscraft.launchercore.util;

public enum LaunchAction {
    HIDE("Hide Launcher"),
    CLOSE("Close Launcher"),
    NOTHING("Stay Open");
    private final String display;

    private LaunchAction(String s) {
        this.display = s;
    }

    @Override
    public String toString() {
        return display;
    }
}
