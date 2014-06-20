package main.java.net.titanscraft.launchercore.mirror.secure;

import main.java.net.titanscraft.launchercore.exception.DownloadException;
import main.java.net.titanscraft.launchercore.install.user.UserModel;
import main.java.net.titanscraft.launchercore.mirror.secure.rest.ISecureMirror;

import java.util.Date;


public class SecureToken {
    private String token;
    private Date receivedTime;
    private UserModel userModel;
    private ISecureMirror mirror;

    private String tokenUserName;
    private String tokenAccessToken;

    public SecureToken(UserModel userModel, ISecureMirror mirror) {
        this.token = null;
        this.receivedTime = null;
        this.userModel = userModel;
        this.mirror = mirror;
    }

    public String getDownloadHost() {
        return mirror.getDownloadHost();
    }

    public String queryForSecureToken() throws DownloadException {
        return null;
//        if (this.token != null && this.receivedTime != null && this.userModel.getCurrentUser() != null /*&& this.tokenUserName != null &&
//                this.tokenAccessToken != null && this.userModel.getCurrentUser().getUsername().equals(this.tokenUserName) &&
//                this.userModel.getCurrentUser().getAccessToken().equals(this.tokenAccessToken)*/) {
//            Date now = new Date();
//            long diffInMinutes = ((now.getTime() - receivedTime.getTime()) / 1000) / 60;
//
//            if (diffInMinutes < 25)
//                return this.token;
//        }
//
//        //We need to hit the mirror for a new token
//        this.token = userModel.retrieveDownloadToken(this.mirror);
//
//        if (this.token != null) {
//            this.receivedTime = new Date();
//            //this.tokenUserName = userModel.getCurrentUser().getUsername();
//            //this.tokenAccessToken = userModel.getCurrentUser().getAccessToken();
//        }
//
//        return this.token;
    }
}
