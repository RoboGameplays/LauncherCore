
package main.java.net.titanscraft.launchercore.minecraft;

import main.java.net.titanscraft.launchercore.util.OperatingSystem;

public class Rule {
    private Action action = Action.ALLOW;
    private OSRestriction os;

    public Action getAction() {
        if (this.os != null && !this.os.matchesCurrentOperatingSystem()) {
            return null;
        }

        return action;
    }

    public static enum Action {
        ALLOW,
        DISALLOW
    }

    public class OSRestriction {
        private OperatingSystem name;
        private String version;

        public boolean matchesCurrentOperatingSystem() {
            if (this.name != null && (this.name != OperatingSystem.getOperatingSystem())) {
                return false;
            }

            boolean matched = true;

            if (this.version != null) {
                String osVersion = System.getProperty("os.version");
                matched = osVersion.matches(this.version);
            }

            return matched;
        }
    }
}
