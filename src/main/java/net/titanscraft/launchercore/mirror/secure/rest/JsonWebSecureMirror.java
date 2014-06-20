package main.java.net.titanscraft.launchercore.mirror.secure.rest;

import main.java.net.titanscraft.launchercore.exception.RestfulAPIException;
import main.java.net.titanscraft.launchercore.restful.RestObject;

public class JsonWebSecureMirror implements ISecureMirror {
    private String baseUrl;
    private String downloadHost;

    public JsonWebSecureMirror(String baseUrl, String downloadHost) {
        this.baseUrl = baseUrl;
        this.downloadHost = downloadHost;
    }

    @Override
    public String getDownloadHost() {
        return downloadHost;
    }

    @Override
    public ValidateResponse validate(ValidateRequest req) {
        String constructedUrl = baseUrl + "validate?a=" + req.getAccessToken() + "&c=" + req.getClientToken();

        try {
            return RestObject.getRestObject(ValidateResponse.class, constructedUrl);
        } catch (RestfulAPIException ex) {
            ex.printStackTrace();
            return new ValidateResponse(ex.getMessage());
        }
    }
}
