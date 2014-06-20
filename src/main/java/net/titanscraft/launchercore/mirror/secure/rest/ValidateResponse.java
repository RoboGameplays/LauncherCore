package main.java.net.titanscraft.launchercore.mirror.secure.rest;

import main.java.net.titanscraft.launchercore.restful.RestObject;

public class ValidateResponse extends RestObject {
    private boolean valid;
    private String message; //error message
    private String clientToken;
    private String accessToken;
    private String downloadToken;

    public ValidateResponse() {
    }

    public ValidateResponse(String errorMessage) {
        this.valid = false;
        this.message = errorMessage;
    }

    public boolean wasValid() {
        return valid;
    }

    public String getErrorMessage() {
        return message;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getDownloadToken() {
        return downloadToken;
    }
}
