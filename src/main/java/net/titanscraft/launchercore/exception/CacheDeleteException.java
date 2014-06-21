
package net.titanscraft.launchercore.exception;

import java.io.IOException;

public class CacheDeleteException extends IOException {
    private Throwable cause;
    String filePath;
    private static final long serialVersionUID = 6462027370292375448L;

    public CacheDeleteException(String filePath) {
        this.filePath = filePath;
    }

    public CacheDeleteException(String filePath, Throwable cause) {
        this.filePath = filePath;
        this.cause = cause;
    }

    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public synchronized Throwable getCause() {
        return this.cause;
    }

    @Override
    public String getMessage() {
        return "Ocorreu um erro ao tentar apagar '" + filePath + "' do cache:";
    }
}
