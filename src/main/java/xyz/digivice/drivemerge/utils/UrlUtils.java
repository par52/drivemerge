package xyz.digivice.drivemerge.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtils {

    private final HttpServletRequest request;

    public UrlUtils(HttpServletRequest request) {
        this.request = request;
    }

    public String getFullUrl() {
        String scheme = request.getScheme(); // http or https
        String host = request.getServerName(); // e.g., example.com

        int port = request.getServerPort();
        String portString = getPortString(port);

        return buildFullUrl(scheme, host, portString);
    }

    private String getPortString(int port) {
        return (port == 80 || port == 443) ? "" : ":" + port;
    }

    private String buildFullUrl(String scheme, String host, String portString) {
        return scheme + "://" + host + portString;
    }
}
