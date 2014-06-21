
package net.titanscraft.launchercore.exception;

import java.io.IOException;

public class PackNotAvailableOfflineException extends IOException {
    private String packDisplayName;
    private Throwable cause;
    private static final long serialVersionUID = 3246491999503435492L;

    public PackNotAvailableOfflineException(String displayName) {
        this.packDisplayName = displayName;
    }

    public PackNotAvailableOfflineException(String displayName, Throwable cause) {
        this.packDisplayName = displayName;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return "O modpack " + packDisplayName + " não parece estar instalado ou está corrompido, e não está disponível para Jogar no modo Offline";
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
