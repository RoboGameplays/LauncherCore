
package main.java.net.titanscraft.launchercore.auth;

public class RefreshRequest extends Response {
    private String accessToken;
    private String clientToken;

    public RefreshRequest() {

    }

    public RefreshRequest(String accessToken, String clientToken) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
    }
}
