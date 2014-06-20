package main.java.net.titanscraft.launchercore.mirror.secure.rest;

import main.java.net.titanscraft.launchercore.restful.RestObject;

public class ValidateRequest extends RestObject {
    private String accessToken;
    private String clientToken;

    public ValidateRequest(String clientToken, String accessToken) {
        this.clientToken = clientToken;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }
}
