
package net.titanscraft.launchercore.install.user;

import com.google.gson.JsonObject;
import net.titanscraft.launchercore.auth.AuthResponse;
import net.titanscraft.launchercore.auth.Profile;
import net.titanscraft.launchercore.util.Utils;

public class User {
    private String username;
    private String accessToken;
    private String clientToken;
    private String displayName;
    private Profile profile;
    private JsonObject userProperties;
    private transient boolean isOffline;

    public User() {
        isOffline = false;
    }

    //This constructor is used to build a user for offline mode
    public User(String username) {
        this.username = username;
        this.displayName = username;
        this.accessToken = "0";
        this.clientToken = "0";
        this.profile = new Profile("0", "");
        this.isOffline = true;
        this.userProperties = Utils.getGson().fromJson("{}", JsonObject.class);
    }

    public User(String username, AuthResponse response) {
        this.isOffline = false;
        this.username = username;
        this.accessToken = response.getAccessToken();
        this.clientToken = response.getClientToken();
        this.displayName = response.getSelectedProfile().getName();
        this.profile = response.getSelectedProfile();

        if (response.getUser() == null) {
            this.userProperties = Utils.getGson().fromJson("{}", JsonObject.class);
        } else {
            this.userProperties = response.getUser().getUserProperties();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Profile getProfile() {
        return profile;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public String getSessionId() {
        return "token:" + accessToken + ":" + profile.getId();
    }

    public void rotateAccessToken(String newToken) {
        this.accessToken = newToken;
    }

    public String getUserPropertiesAsJson() {
        return Utils.getGson().toJson(this.userProperties);
    }
}
