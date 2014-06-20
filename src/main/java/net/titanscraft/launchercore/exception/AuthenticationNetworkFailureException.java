
package main.java.net.titanscraft.launchercore.exception;

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
        return "An error was raised while attempting to communicate with auth.minecraft.net.";
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
