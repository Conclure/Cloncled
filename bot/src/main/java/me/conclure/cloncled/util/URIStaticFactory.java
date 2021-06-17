package me.conclure.cloncled.util;

import java.net.URI;

public interface URIStaticFactory {
  static URI create(String string) {
    try {
      return URI.create(string);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
