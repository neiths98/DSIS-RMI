package utils;

import java.util.UUID;

public final class Utils {
  
  private Utils() {}

  public static String formatServerName(String serverName) {
    return serverName.trim().replaceAll(" ", "-").toUpperCase();
  }

  public static boolean isUUID(String id) {
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

}
