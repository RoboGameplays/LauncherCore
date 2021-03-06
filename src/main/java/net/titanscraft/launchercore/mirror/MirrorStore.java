package net.titanscraft.launchercore.mirror;

import net.titanscraft.launchercore.exception.DownloadException;
import net.titanscraft.launchercore.install.user.UserModel;
import net.titanscraft.launchercore.mirror.download.Download;
import net.titanscraft.launchercore.mirror.secure.SecureToken;
import net.titanscraft.launchercore.mirror.secure.rest.ISecureMirror;
import net.titanscraft.launchercore.util.DownloadListener;
import net.titanscraft.launchercore.util.verifiers.IFileVerifier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class MirrorStore {
    Map<String, SecureToken> secureMirrors = new HashMap<String, SecureToken>();
    private UserModel userModel;

    public MirrorStore(UserModel userModel) {
        this.userModel = userModel;
    }

    public void addSecureMirror(String host, ISecureMirror mirror) {
        SecureToken token = new SecureToken(this.userModel, mirror);

        secureMirrors.put(host.toLowerCase(), token);
    }

    public URL getFullUrl(String url) throws DownloadException {
        URL urlObject = null;

        try {
            urlObject = new URL(url);
        } catch (MalformedURLException ex) {
            throw new DownloadException("URL Invalida: " + url, ex);
        }

        String host = urlObject.getHost().toLowerCase();

        if (secureMirrors.containsKey(host)) {
            SecureToken token = secureMirrors.get(host);

            String downloadKey = token.queryForSecureToken();

            if (downloadKey != null)
                return addDownloadKey(urlObject, token.getDownloadHost(), downloadKey, userModel.getClientToken());
        }

        return urlObject;
    }

    private static final int DOWNLOAD_RETRIES = 3;

    public String getETag(String address) {
        String md5 = "";

        try {
            URL url = getFullUrl(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.162 Safari/535.19");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.162 Safari/535.19");
            HttpURLConnection.setFollowRedirects(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);

            String eTag = conn.getHeaderField("ETag");
            if (eTag != null) {
                eTag = eTag.replaceAll("^\"|\"$", "");
                if (eTag.length() == 32) {
                    md5 = eTag;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return md5;
    }

    public Download downloadFile(String url, String name, String output, File cache, IFileVerifier verifier, DownloadListener listener) throws IOException {
        int tries = DOWNLOAD_RETRIES;
        File outputFile = null;
        Download download = null;
        while (tries > 0) {
            System.out.println("Iniciando download do " + url + ", com " + tries + " tentativas restantes");
            tries--;
            download = new Download(getFullUrl(url), name, output);
            download.setListener(listener);
            download.run();
            if (download.getResult() != Download.Result.SUCCESS) {
                if (download.getOutFile() != null) {
                    download.getOutFile().delete();
                }
                System.err.println("Download of " + url + " Failed!");
                if (listener != null) {
                    listener.stateChanged("Download failed, retries remaining: " + tries, 0F);
                }
            } else {
                if (download.getOutFile().exists() && (verifier == null || verifier.isFileValid(download.getOutFile()))) {
                    outputFile = download.getOutFile();
                    break;
                }
            }
        }
        if (outputFile == null) {
            throw new DownloadException("Failed to download " + url, download != null ? download.getException() : null);
        }
        if (cache != null) {
            FileUtils.copyFile(outputFile, cache);
        }
        return download;
    }

    public Download downloadFile(String url, String name, String output, File cache) throws IOException {
        return downloadFile(url, name, output, cache, null, null);
    }

    public Download downloadFile(String url, String name, String output) throws IOException {
        return downloadFile(url, name, output, null);
    }

    private URL addDownloadKey(URL url, String downloadHost, String downloadKey, String clientId) {
        if (downloadHost != null && !downloadHost.isEmpty()) {
            try {
                url = new URI(url.getProtocol(), url.getUserInfo(), downloadHost, url.getPort(), url.getPath(), url.getQuery(), null).toURL();
            } catch (URISyntaxException ex) {
                //Ignore, just keep old url
            } catch (MalformedURLException ex) {
                //Ignore, just keep old url
            }
        }

        String textUrl = url.toString();

        if (url.getQuery() == null || url.getQuery().isEmpty()) {
            textUrl += "?";
        } else {
            textUrl += "&";
        }

        textUrl += "t=" + downloadKey + "&c=" + clientId;

        try {
            return new URL(textUrl);
        } catch (MalformedURLException ex) {
            throw new Error("Code error: managed to take valid url " + url.toString() + " and turn it into invalid URL " + textUrl);
        }
    }
}
