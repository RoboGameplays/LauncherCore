
package main.java.net.titanscraft.launchercore.auth;

import main.java.net.titanscraft.launchercore.exception.AuthenticationNetworkFailureException;
import main.java.net.titanscraft.launchercore.install.user.User;
import main.java.net.titanscraft.launchercore.util.Utils;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthenticationService {
    private static final String AUTH_SERVER = "https://authserver.mojang.com/";

    public static AuthResponse requestRefresh(User user) throws AuthenticationNetworkFailureException {
        RefreshRequest refreshRequest = new RefreshRequest(user.getAccessToken(), user.getClientToken());
        String data = Utils.getMojangGson().toJson(refreshRequest);

        AuthResponse response;
        try {
            String returned = postJson(AUTH_SERVER + "refresh", data);
            System.out.println(returned);
            response = Utils.getMojangGson().fromJson(returned, AuthResponse.class);
        } catch (IOException e) {
            throw new AuthenticationNetworkFailureException(e);
        }

        return response;
    }

    private static String postJson(String url, String data) throws IOException {
        byte[] rawData = data.getBytes("UTF-8");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("Content-Length", rawData.length + "");
        connection.setRequestProperty("Content-Language", "en-US");

        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.write(rawData);
        writer.flush();
        writer.close();

        InputStream stream = null;
        String returnable = null;
        try {
            stream = connection.getInputStream();
            returnable = IOUtils.toString(stream);
        } catch (IOException e) {
            stream = connection.getErrorStream();

            if (stream == null) {
                throw e;
            }
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
            }
        }

        return returnable;
    }

    public static AuthResponse requestLogin(String username, String password, String clientToken) throws AuthenticationNetworkFailureException {
        Agent agent = new Agent("Minecraft", "1");

        AuthRequest request = new AuthRequest(agent, username, password, clientToken);
        String data = Utils.getMojangGson().toJson(request);

        AuthResponse response;
        try {
            String returned = postJson(AUTH_SERVER + "authenticate", data);
            System.out.println("Auth: " + returned);
            response = Utils.getMojangGson().fromJson(returned, AuthResponse.class);
        } catch (IOException e) {
            throw new AuthenticationNetworkFailureException(e);
        }
        return response;
    }
}
