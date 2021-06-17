package me.conclure.concled.util;

import java.net.URI;

public interface URIHelper {
  static URI create(String string) {
    try {
      return URI.create(string);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
