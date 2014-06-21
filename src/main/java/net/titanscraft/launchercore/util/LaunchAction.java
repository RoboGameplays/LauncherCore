
package net.titanscraft.launchercore.util;

public enum LaunchAction {
    HIDE("Esconder Launcher"),
    CLOSE("Fechar Launcher"),
    NOTHING("Deixar Aberto");
    private final String display;

    private LaunchAction(String s) {
        this.display = s;
    }

    @Override
    public String toString() {
        return display;
    }
}
