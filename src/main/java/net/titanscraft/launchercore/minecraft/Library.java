
package main.java.net.titanscraft.launchercore.minecraft;

import main.java.net.titanscraft.launchercore.mirror.MirrorStore;
import main.java.net.titanscraft.launchercore.util.OperatingSystem;
import main.java.net.titanscraft.launchercore.util.Utils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.List;
import java.util.Map;

public class Library {
    private static final StrSubstitutor SUBSTITUTOR = new StrSubstitutor();
    private static final String[] fallback = {"http://mirror.technicpack.net/Technic/lib/", "http://search.maven.org/remotecontent?filepath="};
    private String name;
    private List<Rule> rules;
    private Map<OperatingSystem, String> natives;
    private ExtractRules extract;
    private String url;

    public String getName() {
        return name;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Map<OperatingSystem, String> getNatives() {
        return natives;
    }

    public ExtractRules getExtract() {
        return extract;
    }

    public boolean isForCurrentOS() {
        if (rules == null) {
            return true;
        }

        Rule.Action lastAction = Rule.Action.DISALLOW;

        for (Rule rule : rules) {
            Rule.Action action = rule.getAction();
            if (action != null) {
                lastAction = action;
            }
        }

        return lastAction.equals(Rule.Action.ALLOW);
    }

    public String getArtifactPath() {
        return getArtifactPath(null);
    }

    public String getArtifactPath(String classifier) {
        if (this.name == null) {
            throw new IllegalStateException("Cannot get artifact path of empty/blank artifact");
        }
        return String.format("%s/%s", getArtifactBaseDir(), getArtifactFilename(classifier));
    }

    public String getArtifactBaseDir() {
        if (this.name == null) {
            throw new IllegalStateException("Cannot get artifact dir of empty/blank artifact");
        }
        String[] parts = this.name.split(":", 3);
        return String.format("%s/%s/%s", parts[0].replaceAll("\\.", "/"), parts[1], parts[2]);
    }

    public String getArtifactFilename(String classifier) {
        if (this.name == null) {
            throw new IllegalStateException("Cannot get artifact filename of empty/blank artifact");
        }

        String[] parts = this.name.split(":", 3);
        String result;

        if (classifier != null) {
            result = String.format("%s-%s%s.jar", parts[1], parts[2], "-" + classifier);
        } else {
            result = String.format("%s-%s%s.jar", parts[1], parts[2], "");
        }

        return SUBSTITUTOR.replace(result);
    }

    public String getDownloadUrl(String path, MirrorStore mirrorStore) {
        if (this.url != null) {
            String checkUrl = url + path;
            if (Utils.pingHttpURL(checkUrl, mirrorStore)) {
                return checkUrl;
            }
            for (String string : fallback) {
                checkUrl = string + path;
                if (Utils.pingHttpURL(checkUrl, mirrorStore)) {
                    return checkUrl;
                }
            }
        }
        return " https://libraries.minecraft.net/" + path;
    }
}
