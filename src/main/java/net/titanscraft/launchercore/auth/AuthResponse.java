
package net.titanscraft.launchercore.auth;

import java.util.Arrays;

public class AuthResponse extends Response {
    private String accessToken;
    private String clientToken;
    private Profile[] availableProfiles;
    private Profile selectedProfile;
    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public Profile[] getAvailableProfiles() {
        return availableProfiles;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getError() {
        String error = super.getError();

        if (this.availableProfiles != null && this.availableProfiles.length == 0 && (error == null || error.isEmpty())) {
            return "Sem licença de Minecraft";
        } else {
            return error;
        }
    }

    @Override
    public String getErrorMessage() {
        String message = super.getErrorMessage();

        if (this.availableProfiles != null && this.availableProfiles.length == 0 && (message == null || message.isEmpty())) {
            return "Esta conta Mojang não tem de copia Minecraft anexada.";
        } else {
            return message;
        }
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", clientToken='" + clientToken + '\'' +
                ", availableProfiles=" + Arrays.toString(availableProfiles) +
                ", selectedProfile=" + selectedProfile +
                '}';
    }
}
