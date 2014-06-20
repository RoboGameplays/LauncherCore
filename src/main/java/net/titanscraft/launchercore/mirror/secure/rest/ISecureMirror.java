package net.titanscraft.launchercore.mirror.secure.rest;

public interface ISecureMirror {
    String getDownloadHost();

    ValidateResponse validate(ValidateRequest req);
}
