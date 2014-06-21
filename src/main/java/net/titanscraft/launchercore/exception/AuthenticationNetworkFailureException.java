
package net.titanscraft.launchercore.exception;

import java.io.IOException;

public class AuthenticationNetworkFailureException extends IOException {
    private Throwable cause;
    private static final long serialVersionUID = 5887385045789342851L;

    public AuthenticationNetworkFailureException() {

    }

    public AuthenticationNetworkFailureException(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return "Um erro foi gerado durante a tentativa de se comunicar com auth.minecraft.net.";
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
