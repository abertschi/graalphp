package org.graalphp.launcher;

/**
 * This file is enhanced by templating-maven-plugin.
 *
 * @author abertschi
 */
public class LauncherVersion {

    private static final String MVN_VERSION = "${project.version}";
    private static final String BUILD_ID = "${buildTimestamp}";
    private static final String GIT = "${commitId}";

    private LauncherVersion() {
    }

    public static String getVersion() {
        return MVN_VERSION;
    }

    public static String getBuildId() {
        return BUILD_ID;
    }

    public static String getGit() {
        return GIT;
    }

    public static String getCuratedVersion() {
        StringBuilder msg = new StringBuilder();
        msg.append("graalphp ")
                .append(getVersion())
                .append(" based on git ")
                .append(getGit())
                .append(" built at ")
                .append(getBuildId())
                .append(".");
        return msg.toString();
    }
}
